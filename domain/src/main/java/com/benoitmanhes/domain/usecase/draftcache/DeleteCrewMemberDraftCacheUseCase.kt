package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class DeleteCrewMemberDraftCacheUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
) : CTUseCase() {
    suspend operator fun invoke(
        draftCacheId: String,
        crewRef: String,
    ) {
        runCatch {
            val draftCache = draftCacheRepository.getDraftCache(draftCacheId) ?: throw CTDomainError.Code.UNEXPECTED.error()

            (draftCache.type as? DraftCache.Type.Coop)?.let { coopType ->
                // Delete crew’s member
                val newMap = coopType.crewDraftStepsMap.toMutableMap().apply {
                    remove(crewRef)
                }

                // Save draftCache
                saveDraftCacheUseCase(
                    draftCache.copy(
                        type = coopType.copy(crewDraftStepsMap = newMap)
                    )
                )

                // Delete crew’s member steps
                coopType.crewDraftStepsMap[crewRef]?.let { draftCacheStepRepository::deleteDraftCacheSteps }
            }
        }
    }
}
