package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.Cache

interface CacheClassicalRemoteDataSource {
    suspend fun getAllCaches(): List<Cache.Classical>
    suspend fun saveCache(cache: Cache.Classical)
}