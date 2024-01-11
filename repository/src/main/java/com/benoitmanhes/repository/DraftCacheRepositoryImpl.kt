package com.benoitmanhes.repository

import com.benoitmanhes.domain.interfaces.localdatasource.DraftCacheLocalDataSource
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.model.DraftCache
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DraftCacheRepositoryImpl @Inject constructor(
    private val draftCacheLocalDataSource: DraftCacheLocalDataSource,
) : DraftCacheRepository {
    override suspend fun getDraftCache(draftCacheId: String): DraftCache? =
        draftCacheLocalDataSource.getDraftCache(draftCacheId)

    override fun getDraftCacheFlow(draftCacheId: String): Flow<DraftCache?> =
        draftCacheLocalDataSource.getDraftCacheFlow(draftCacheId)

    override suspend fun deleteDraftCache(draftCacheId: String) {
        draftCacheLocalDataSource.deleteDraftCache(draftCacheId)
    }

    override suspend fun saveDraftCache(draftCache: DraftCache) {
        draftCacheLocalDataSource.saveDraftCache(draftCache)
    }

    override fun getAllDraftCacheFlow(): Flow<List<DraftCache>> =
        draftCacheLocalDataSource.getAllDraftCacheFlow()
}
