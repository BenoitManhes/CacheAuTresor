package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.uimodel.UIDraftStepDetail
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

class GetUIDraftStepDetailUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
) : CTUseCase() {

    operator fun invoke(draftCacheId: String, draftStepId: String): Flow<CTResult<UIDraftStepDetail>> = useCaseFlow {
        emitAll(
            combine(
                draftCacheRepository.getDraftCacheFlow(draftCacheId),
                draftCacheStepRepository.getDraftCacheStepFlow(dratCacheStepId = draftStepId),
            ) { draftCache, draftStep ->
                draftCache ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()
                draftStep ?: throw CTDomainError.Code.CACHE_STEP_NOT_FOUND.error()

                val uiDraftStepDetail = UIDraftStepDetail(
                    draftCache = draftCache,
                    draftStep = draftStep,
                    type = getDraftStepType(draftCache, draftStepId),
                )
                CTResult.Success(uiDraftStepDetail)
            }
        )
    }

    private fun getDraftStepType(draftCache: DraftCache, draftStepId: String): UIDraftStepDetail.Type =
        when {
            draftCache.type == DraftCache.Type.Classical -> UIDraftStepDetail.Type.Classical
            draftCache.finalStepRef == draftStepId -> UIDraftStepDetail.Type.Final
            draftCache.type is DraftCache.Type.Mystery -> UIDraftStepDetail.Type.MysteryEnigma
            draftCache.type is DraftCache.Type.Piste -> UIDraftStepDetail.Type.Piste(
                index = draftCache.type.intermediateDraftStepIds.indexOf(draftStepId)
            )

            draftCache.type is DraftCache.Type.Coop -> {
                val (crewRef, index) = draftCache.type.crewDraftStepsMap
                    .firstNotNullOf { (key, refList) ->
                        (key to refList.indexOf(draftStepId)).takeIf { it.second >= 0 }
                    }
                UIDraftStepDetail.Type.Coop(
                    index = index,
                    crewRef = crewRef,
                )
            }

            else -> UIDraftStepDetail.Type.Final
        }
}
