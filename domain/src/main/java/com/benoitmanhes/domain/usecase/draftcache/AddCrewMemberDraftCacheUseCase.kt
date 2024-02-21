package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.domain.usecase.CTUseCase
import java.util.UUID
import javax.inject.Inject

class AddCrewMemberDraftCacheUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
) : CTUseCase() {

    suspend operator fun invoke(
        draftCacheId: String,
        crewMemberRef: String,
    ): CTSuspendResult<Unit> = runCatchSuspendResult {
        val draftCache = draftCacheRepository.getDraftCache(draftCacheId = draftCacheId) ?: throw CTDomainError.Code.UNEXPECTED.error()

        (draftCache.type as? DraftCache.Type.Coop)?.let { coopType ->
            val newMap = coopType.crewDraftStepsMap.toMutableMap()

            // Check newMemberName is available
            if (newMap.contains(crewMemberRef)) throw CTDomainError.Code.CREW_MEMBER_NAME_UNAVAILABLE.error()

            // Create the first crewMember step
            val firstStep = DraftCacheStep(
                stepDraftId = UUID.randomUUID().toString(),
                instruction = null,
                clue = null,
                validationCode = null,
                coordinates = null,
            )
            draftCacheStepRepository.saveDraftCacheStep(firstStep)

            // Add crewMember map entry
            newMap[crewMemberRef] = listOf(firstStep.stepDraftId)

            // Save draftCache
            saveDraftCacheUseCase(
                draftCache.copy(
                    type = coopType.copy(crewDraftStepsMap = newMap)
                )
            )
        }
        CTSuspendResult.Success(Unit)
    }
}
