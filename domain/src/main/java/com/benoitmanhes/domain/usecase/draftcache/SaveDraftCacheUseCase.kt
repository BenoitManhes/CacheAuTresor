package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class SaveDraftCacheUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheGetAllStepUseCase: DraftCacheGetAllStepUseCase,
    private val calculateDraftCacheProgressUseCase: CalculateDraftCacheProgressUseCase,
) : CTUseCase() {
    suspend operator fun invoke(draftCache: DraftCache): CTSuspendResult<Unit> = runCatchSuspendResult {
        val newProgress = calculateDraftCacheProgressUseCase(
            draftCache = draftCache,
            steps = draftCacheGetAllStepUseCase(draftCache),
        )
        draftCacheRepository.saveDraftCache(draftCache.copy(progress = newProgress))
        CTSuspendResult.Success(Unit)
    }
}
