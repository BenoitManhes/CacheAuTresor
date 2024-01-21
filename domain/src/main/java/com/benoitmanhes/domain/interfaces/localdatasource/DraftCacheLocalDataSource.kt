package com.benoitmanhes.domain.interfaces.localdatasource

import com.benoitmanhes.domain.model.DraftCache
import kotlinx.coroutines.flow.Flow

interface DraftCacheLocalDataSource {
    suspend fun getDraftCache(draftCacheId: String): DraftCache?
    fun getDraftCacheFlow(draftCacheId: String): Flow<DraftCache?>
    suspend fun deleteDraftCache(draftCacheId: String)
    suspend fun clearAll()
    suspend fun saveDraftCache(draftCache: DraftCache)
    fun getAllDraftCacheFlow(): Flow<List<DraftCache>>
}
