package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class SaveDraftCacheUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val calculateDraftCacheProgressUseCase: CalculateDraftCacheProgressUseCase,
) : CTUseCase() {
    suspend operator fun invoke(draftCache: DraftCache): CTSuspendResult<Unit> = runCatchSuspendResult {
        val newProgress = calculateDraftCacheProgressUseCase(
            draftCache = draftCache,
            steps = getAllDraftCacheStep(draftCache),
        )
        draftCacheRepository.saveDraftCache(draftCache.copy(progress = newProgress))
        CTSuspendResult.Success(Unit)
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
