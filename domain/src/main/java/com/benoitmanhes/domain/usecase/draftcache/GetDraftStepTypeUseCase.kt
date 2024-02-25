package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.uimodel.UIDraftStepDetail
import javax.inject.Inject

class GetDraftStepTypeUseCase @Inject constructor() {
    operator fun invoke(draftCache: DraftCache, draftStepId: String): UIDraftStepDetail.Type =
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
