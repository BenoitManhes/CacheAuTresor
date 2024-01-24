package com.benoitmanhes.domain.interfaces.localdatasource

import com.benoitmanhes.domain.model.Cache
import kotlinx.coroutines.flow.Flow

interface CacheLocalDataSource {
    suspend fun getCache(cacheId: String): Cache?
    fun getCacheFlow(cacheId: String): Flow<Cache?>

    fun getAllCachesFlow(): Flow<List<Cache>>
    suspend fun getAllCaches(): List<Cache>
    fun getAllCachesByExplorer(explorerId: String): Flow<List<Cache>>

    suspend fun saveCache(cache: Cache)
    suspend fun saveCaches(caches: List<Cache>)
}
