package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import javax.inject.Inject

class UpdateDraftCacheProgressUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
) {
    suspend operator fun invoke(draftCacheId: String) {
        draftCacheRepository.getDraftCache(draftCacheId)?.let { draftCache ->
            saveDraftCacheUseCase(draftCache)
        }
    }
}
