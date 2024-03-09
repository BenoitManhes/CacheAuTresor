package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.math.absoluteValue
import kotlin.random.Random

class NewDraftStepUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
) : CTUseCase() {

    operator fun invoke(draftCacheId: String, crewRef: String? = null): Flow<CTResult<Unit>> = useCaseFlow {
        val draftCache = draftCacheRepository.getDraftCache(draftCacheId) ?: throw CTDomainError.Code.UNEXPECTED.error()
        val newStep = newStep()

        val typeUpdated = when (draftCache.type) {
            null,
            DraftCache.Type.Classical,
            is DraftCache.Type.Mystery,
            -> throw CTDomainError.Code.UNEXPECTED.error("Draftcache type not allow to add new draft step")

            is DraftCache.Type.Piste -> draftCache.type.copy(
                intermediateDraftStepIds = draftCache.type.intermediateDraftStepIds + newStep.stepDraftId,
            )

            is DraftCache.Type.Coop -> {
                if (crewRef.isNullOrBlank()) throw CTDomainError.Code.UNEXPECTED.error("crewRef required")
                val newMap = draftCache.type.crewDraftStepsMap.toMutableMap()
                val crewStepRef = newMap[crewRef] ?: throw CTDomainError.Code.UNEXPECTED.error("invalid crewRef")
                newMap[crewRef] = crewStepRef + newStep.stepDraftId

                draftCache.type.copy(crewDraftStepsMap = newMap)
            }
        }

        draftCacheStepRepository.saveDraftCacheStep(newStep)
        saveDraftCacheUseCase(draftCache.copy(type = typeUpdated))
        emit(CTResult.Success(Unit))
    }

    private fun newStep(): DraftCacheStep = DraftCacheStep(
        stepDraftId = Random.nextLong().absoluteValue.toString(36).take(8),
        instruction = null,
        clue = null,
        validationCode = null,
        coordinates = null,
    )
}
