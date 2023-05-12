package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.Cache

interface CacheRepository {
    suspend fun getCache(cacheId: String): Cache?
    suspend fun getAllCaches(): List<Cache>
    suspend fun saveCache(cache: Cache)
}
