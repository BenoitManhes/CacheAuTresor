package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.domain.usecase.CTUseCase
import java.util.UUID
import javax.inject.Inject

class ChangeDraftCacheTypeUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
) : CTUseCase() {
    suspend operator fun invoke(draftCacheId: String, newType: String): CTSuspendResult<Unit> = runCatchSuspendResult {
        val draftCache = draftCacheRepository.getDraftCache(draftCacheId) ?: throw CTDomainError.Code.UNEXPECTED.error()

        // Check new type exist
        val check = newType in setOf(
            DraftCache.Type.Classical::class.java.simpleName,
            DraftCache.Type.Mystery::class.java.simpleName,
            DraftCache.Type.Coop::class.java.simpleName,
            DraftCache.Type.Piste::class.java.simpleName,
        )
        if (!check) throw CTDomainError.Code.UNEXPECTED.error("DraftCache.Type unknown: $newType")

        // Check new type is different from current one
        if (draftCache.type != null && draftCache.type::class.java.simpleName == newType) {
            return@runCatchSuspendResult CTSuspendResult.Success(Unit)
        }

        // Create new type
        val oldType = draftCache.type
        val draftCacheType = when (newType) {
            DraftCache.Type.Classical::class.java.simpleName -> DraftCache.Type.Classical
            DraftCache.Type.Mystery::class.java.simpleName -> DraftCache.Type.Mystery(
                enigmaDraftStepId = createNewStep(draftCache.coordinates),
            )

            DraftCache.Type.Piste::class.java.simpleName -> DraftCache.Type.Piste(
                intermediateDraftStepIds = listOf(
                    createNewStep(draftCache.coordinates),
                ),
            )

            DraftCache.Type.Coop::class.java.simpleName -> DraftCache.Type.Coop(crewDraftStepsMap = emptyMap())

            else -> throw CTDomainError.Code.UNEXPECTED.error()
        }

        // Update final step
        val finalStep = draftCache.finalStepRef?.let {
            draftCacheStepRepository.getDraftCacheStep(it)
        } ?: DraftCacheStep(
            stepDraftId = UUID.randomUUID().toString(),
            instruction = null,
            clue = null,
            coordinates = null,
            validationCode = null,
        )
        finalStep.copy(
            coordinates = when (draftCacheType) {
                DraftCache.Type.Classical -> draftCache.coordinates
                else -> finalStep.coordinates.takeIf { finalStep.coordinates != draftCache.coordinates }
            }
        ).also {
            draftCacheStepRepository.saveDraftCacheStep(it)
        }

        // Save and update draft cache
        val draftCacheUpdated = draftCache.copy(
            type = draftCacheType,
            finalStepRef = finalStep.stepDraftId,
        )
        saveDraftCacheUseCase(draftCacheUpdated)

        // Delete old intermediarySteps
        oldType?.let { type ->
            when (type) {
                is DraftCache.Type.Classical -> emptyList()
                is DraftCache.Type.Coop -> type.crewDraftStepsMap.values.flatten()
                is DraftCache.Type.Mystery -> listOfNotNull(type.enigmaDraftStepId)
                is DraftCache.Type.Piste -> type.intermediateDraftStepIds
            }
        }?.also { intermediarySteps ->
            draftCacheStepRepository.deleteDraftCacheSteps(intermediarySteps)
        }

        CTSuspendResult.Success(Unit)
    }

    /**
     * Create a new DraftCacheStep and return the draftCacheStepId
     */
    private suspend fun createNewStep(coordinates: Coordinates? = null): String {
        val newDraftCacheStep = DraftCacheStep(
            stepDraftId = UUID.randomUUID().toString(),
            instruction = null,
            clue = null,
            coordinates = coordinates,
            validationCode = null,
        )
        draftCacheStepRepository.saveDraftCacheStep(newDraftCacheStep)
        return newDraftCacheStep.stepDraftId
    }
}
