package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.Cache

interface CacheCoopRemoteDataSource {
    suspend fun getAllCaches(): List<Cache.Coop>
    suspend fun saveCache(cache: Cache.Coop)
}