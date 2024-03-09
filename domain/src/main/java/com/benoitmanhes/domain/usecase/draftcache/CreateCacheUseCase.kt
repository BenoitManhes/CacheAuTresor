package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.common.kotlin.extensions.nullIfBlank
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.extension.removeAccent
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheStep
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.domain.model.InstructionContent
import com.benoitmanhes.domain.synchronization.SynchronizeCacheUseCase
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.cache.IsValidFinalCoordinatesUseCase
import com.benoitmanhes.domain.usecase.cache.IsValidInitCoordinatesUseCase
import com.benoitmanhes.domain.usecase.explorer.GetMyExplorerUseCase
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import kotlin.math.absoluteValue
import kotlin.random.Random

class CreateCacheUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val explorerRepository: ExplorerRepository,
    private val getMyExplorerUseCase: GetMyExplorerUseCase,
    private val cacheRepository: CacheRepository,
    private val stepRepository: StepRepository,
    private val draftCacheGetAllStepUseCase: DraftCacheGetAllStepUseCase,
    private val synchronizeCacheUseCase: SynchronizeCacheUseCase,
    private val isValidInitCoordinatesUseCase: IsValidInitCoordinatesUseCase,
    private val isValidFinalCoordinatesUseCase: IsValidFinalCoordinatesUseCase,
) : CTUseCase() {
    operator fun invoke(draftCacheId: String): Flow<CTResult<Cache>> = resultFlow {
        val draftCache = draftCacheRepository.getDraftCache(draftCacheId) ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()
        val me = getMyExplorerUseCase() ?: throw CTDomainError.Code.UNEXPECTED.error()

        // Convert draft cache
        val type = draftCache.type?.toCacheType() ?: throw CTDomainError.Code.DRAFT_CACHE_MISSING_FIELD.error()
        val cacheId = generateCacheId(type)
        val cache = Cache(
            cacheId = cacheId,
            creatorId = me.explorerId,
            title = draftCache.title?.nullIfBlank() ?: throw CTDomainError.Code.DRAFT_CACHE_MISSING_FIELD.error(),
            coordinates = draftCache.coordinates ?: throw CTDomainError.Code.DRAFT_CACHE_MISSING_FIELD.error(),
            difficulty = draftCache.difficulty ?: throw CTDomainError.Code.DRAFT_CACHE_MISSING_FIELD.error(),
            ground = draftCache.ground ?: throw CTDomainError.Code.DRAFT_CACHE_MISSING_FIELD.error(),
            size = draftCache.size ?: throw CTDomainError.Code.DRAFT_CACHE_MISSING_FIELD.error(),
            discovered = false,
            createDate = Date(),
            cacheIdsRequired = listOf(),
            tagIds = listOf(),
            finalStepRef = draftCache.finalStepRef?.nullIfBlank() ?: throw CTDomainError.Code.DRAFT_CACHE_MISSING_FIELD.error(),
            description = draftCache.description?.nullIfBlank() ?: throw CTDomainError.Code.DRAFT_CACHE_MISSING_FIELD.error(),
            lockDescription = draftCache.lockDescription?.nullIfBlank() ?: throw CTDomainError.Code.DRAFT_CACHE_MISSING_FIELD.error(),
            lockCode = draftCache.lockCode?.codeSimplifiedFormat()?.nullIfBlank()
                ?: throw CTDomainError.Code.DRAFT_CACHE_MISSING_FIELD.error(),
            type = type,
        )

        // Convert draft step
        val draftSteps = draftCacheGetAllStepUseCase(draftCache)
        val stepsCache = draftSteps.map { it.toCacheStep() }

        // Check forbidden place
        synchronizeCacheUseCase(forceSynchro = true)
        if (!isValidInitCoordinatesUseCase(
                cache.coordinates
            )
        ) {
            throw CTDomainError.Code.DRAFT_CACHE_INIT_COORDINATES_INVALID.error()
        }
        val lastCoordinate = stepsCache.first { it.stepId == cache.finalStepRef }.coordinates
        if (!isValidFinalCoordinatesUseCase(
                lastCoordinate
            )
        ) {
            throw CTDomainError.Code.DRAFT_CACHE_FINAL_COORDINATES_INVALID.error()
        }

        // Save cache data
        stepRepository.saveSteps(stepsCache)
        cacheRepository.saveCache(cache)

        // Delete draft data
        runCatch {
            draftCacheRepository.deleteDraftCache(draftCacheId)
            draftCacheStepRepository.deleteDraftCacheSteps(draftSteps.map { it.stepDraftId })
        }

        // Update user
        runCatch {
            val myCacheMap = me.cachesMap.toMutableMap()
            myCacheMap[cache.cacheId] = 0
            val explorerUpdated = me.copy(
                cachesMap = myCacheMap
            )
            explorerRepository.saveExplorer(explorerUpdated)
        }
        cache
    }

    private fun DraftCacheStep.toCacheStep(): CacheStep = CacheStep(
        stepId = stepDraftId,
        instruction = listOf(
            instruction?.nullIfBlank()?.let(InstructionContent::Text) ?: throw CTDomainError.Code.DRAFT_CACHE_STEP_INCOMPLETE.error(),
        ),
        clue = clue,
        validationCode = validationCode?.nullIfBlank()?.codeSimplifiedFormat()
            ?: throw CTDomainError.Code.DRAFT_CACHE_STEP_INCOMPLETE.error(),
        coordinates = coordinates ?: throw CTDomainError.Code.DRAFT_CACHE_STEP_INCOMPLETE.error(),
    )

    private fun DraftCache.Type.toCacheType(): Cache.Type = when (this) {
        DraftCache.Type.Classical -> Cache.Type.Classical
        is DraftCache.Type.Piste -> Cache.Type.Piste(
            this.intermediateDraftStepIds.ifEmpty { throw CTDomainError.Code.DRAFT_CACHE_MISSING_STEP.error() }
        )

        is DraftCache.Type.Mystery -> Cache.Type.Mystery(
            this.enigmaDraftStepId ?: throw CTDomainError.Code.DRAFT_CACHE_MISSING_FIELD.error()
        )

        is DraftCache.Type.Coop -> Cache.Type.Coop(
            crewStepsMap = this.crewDraftStepsMap.also { map ->
                when {
                    map.size < 2 -> throw CTDomainError.Code.DRAFT_CACHE_MISSING_CREW_MEMBER.error()
                    map.any { it.value.isEmpty() } -> throw CTDomainError.Code.DRAFT_CACHE_MISSING_STEP.error()
                }
            }
        )
    }

    private fun generateCacheId(type: Cache.Type): String {
        val prefix = when (type) {
            Cache.Type.Classical -> "CL"
            is Cache.Type.Mystery -> "MY"
            is Cache.Type.Piste -> "PI"
            is Cache.Type.Coop -> "CO"
        }

        val suffix = Random.nextLong().absoluteValue.toString(36).take(8).uppercase()
        return "$prefix-$suffix"
    }

    private fun String.codeSimplifiedFormat(): String = this
        .removeAccent()
        .lowercase()
}
