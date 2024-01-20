package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.Cache
import kotlinx.coroutines.flow.Flow

interface CacheRepository {
    suspend fun getCache(cacheId: String): Cache?
    fun getCacheFlow(cacheId: String): Flow<Cache?>
    fun getAllCachesFlow(): Flow<List<Cache>>
    fun getAllCachesByExplorer(explorerId: String): Flow<List<Cache>>
    suspend fun fetchAllCaches(): Unit
    suspend fun saveCache(cache: Cache)
}
