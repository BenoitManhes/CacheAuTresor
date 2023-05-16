package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.Cache

interface CacheRemoteDataSource {
    suspend fun getCache(cacheId: String): Cache?
    suspend fun getAllCaches(): List<Cache>
    suspend fun saveCache(cache: Cache)
}
