package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.domain.uimodel.UIDraftStepDetail
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class GetAllDraftStepDetailUseCase @Inject constructor(
    private val getDraftStepTypeUseCase: GetDraftStepTypeUseCase,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val draftCacheRepository: DraftCacheRepository,
) : CTUseCase() {

    suspend operator fun invoke(draftCacheId: String): List<UIDraftStepDetail> =
        invoke(draftCacheRepository.getDraftCache(draftCacheId)!!)

    suspend operator fun invoke(draftCache: DraftCache): List<UIDraftStepDetail> =
        getAllDraftCacheStep(draftCache).map { draftStep ->
            UIDraftStepDetail(
                draftCache = draftCache,
                draftStep = draftStep,
                type = getDraftStepTypeUseCase(draftCache, draftStep.stepDraftId),
            )
        }

    private suspend fun getAllDraftCacheStep(draftCache: DraftCache): List<DraftCacheStep> {
        val finalStep = draftCache.finalStepRef?.let { draftCacheStepRepository.getDraftCacheStep(it) }
        val intermediarySteps = draftCache.type?.let { type ->
            when (type) {
                is DraftCache.Type.Classical -> emptyList()
                is DraftCache.Type.Coop -> type.crewDraftStepsMap.values.flatten()
                is DraftCache.Type.Mystery -> listOfNotNull(type.enigmaDraftStepId)
                is DraftCache.Type.Piste -> type.intermediateDraftStepIds
            }.mapNotNull { draftCacheStepRepository.getDraftCacheStep(it) }
        } ?: emptyList()

        return (intermediarySteps + finalStep).filterNotNull()
    }
}
