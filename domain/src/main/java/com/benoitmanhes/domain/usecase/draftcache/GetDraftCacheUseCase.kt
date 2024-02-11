package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDraftCacheUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
) : CTUseCase() {
    fun asFlow(draftCacheId: String): Flow<DraftCache?> =
        draftCacheRepository.getDraftCacheFlow(draftCacheId)
            .useCaseCatch { null }

    suspend operator fun invoke(draftCacheId: String): DraftCache? = runCatchNullable {
        draftCacheRepository.getDraftCache(draftCacheId)
    }
}
