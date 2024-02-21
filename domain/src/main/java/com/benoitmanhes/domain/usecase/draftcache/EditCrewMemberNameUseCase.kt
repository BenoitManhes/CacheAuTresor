package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class EditCrewMemberNameUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
) : CTUseCase() {

    suspend operator fun invoke(
        draftCacheId: String,
        crewMemberRef: String,
        newCrewMemberName: String,
    ): CTSuspendResult<Unit> = runCatchSuspendResult {
        val draftCache = draftCacheRepository.getDraftCache(draftCacheId = draftCacheId) ?: throw CTDomainError.Code.UNEXPECTED.error()

        // Return if old == new
        if (crewMemberRef == newCrewMemberName) return@runCatchSuspendResult CTSuspendResult.Success(Unit)

        (draftCache.type as? DraftCache.Type.Coop)?.let { coopType ->
            val newMap = coopType.crewDraftStepsMap.toMutableMap()

            // Check newMemberName is available
            if (newMap.contains(newCrewMemberName)) throw CTDomainError.Code.CREW_MEMBER_NAME_UNAVAILABLE.error()

            // Replace crewMemberRef
            if (newMap.contains(crewMemberRef)) {
                newMap[newCrewMemberName] = newMap.remove(crewMemberRef)!!
            }

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
