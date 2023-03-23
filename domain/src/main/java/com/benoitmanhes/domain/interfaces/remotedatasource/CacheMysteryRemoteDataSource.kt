package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.Cache

interface CacheMysteryRemoteDataSource {
    suspend fun getAllCaches(): List<Cache.Mystery>
    suspend fun saveCache(cache: Cache.Mystery)
}