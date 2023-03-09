package com.benoitmanhes.domain.interfaces.localdatasource

import com.benoitmanhes.domain.model.Cache
import kotlinx.coroutines.flow.Flow

interface CacheLocalDataSource {
    suspend fun saveCache(cache: Cache)
    suspend fun getCache(cacheId: String): Cache
    fun getCacheFlow(cache: Cache): Flow<Cache>
    fun deleteCache(cacheId: String)
}
