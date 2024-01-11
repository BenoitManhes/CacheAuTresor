package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.DraftCache
import kotlinx.coroutines.flow.Flow

interface DraftCacheRepository {
    suspend fun getDraftCache(draftCacheId: String): DraftCache?
    fun getDraftCacheFlow(draftCacheId: String): Flow<DraftCache?>
    suspend fun deleteDraftCache(draftCacheId: String)
    suspend fun saveDraftCache(draftCache: DraftCache)
    fun getAllDraftCacheFlow(): Flow<List<DraftCache>>
}
