package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.extensions.throwIfFailure
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.uimodel.UIDraftStepDetail
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteDraftStepUseCase @Inject constructor(
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
    private val draftCacheRepository: DraftCacheRepository,
    private val getDraftStepTypeUseCase: GetDraftStepTypeUseCase,
    private val draftCacheStepRepository: DraftCacheStepRepository,
) : CTUseCase() {
    operator fun invoke(draftCacheId: String, draftStepId: String): Flow<CTResult<Unit>> = useCaseFlow {
        val draftCache = draftCacheRepository.getDraftCache(draftCacheId) ?: throw CTDomainError.Code.UNEXPECTED.error()
        val type = getDraftStepTypeUseCase(draftCache = draftCache, draftStepId = draftStepId)

        when (type) {
            UIDraftStepDetail.Type.Classical,
            UIDraftStepDetail.Type.MysteryEnigma,
            UIDraftStepDetail.Type.Final,
            -> throw CTDomainError.Code.UNEXPECTED.error()

            is UIDraftStepDetail.Type.Coop,
            is UIDraftStepDetail.Type.Piste,
            -> {
            } // nothing
        }

        val draftCacheUpdated = when (draftCache.type) {
            null,
            is DraftCache.Type.Classical,
            is DraftCache.Type.Mystery,
            -> throw CTDomainError.Code.UNEXPECTED.error()

            is DraftCache.Type.Piste -> draftCache.copy(
                type = draftCache.type.copy(
                    intermediateDraftStepIds = draftCache.type.intermediateDraftStepIds.toMutableList().apply {
                        remove(draftStepId)
                    },
                )
            )

            is DraftCache.Type.Coop -> {
                val newMap = draftCache.type.crewDraftStepsMap.toMutableMap()
                newMap.values.forEach {
                    it.toMutableList().remove(draftStepId)
                    it.toList()
                }
                newMap.entries.removeIf { it.value.isEmpty() }

                draftCache.copy(
                    type = draftCache.type.copy(
                        crewDraftStepsMap = newMap.toMap(),
                    ),
                )
            }
        }

        saveDraftCacheUseCase(draftCacheUpdated).throwIfFailure()
        draftCacheStepRepository.deleteDraftCacheStep(draftStepId)

        emit(CTResult.Success(Unit))
    }
}