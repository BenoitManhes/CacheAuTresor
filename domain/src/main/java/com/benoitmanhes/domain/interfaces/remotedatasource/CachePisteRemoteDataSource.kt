package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.Cache

interface CachePisteRemoteDataSource {
    suspend fun getAllCaches(): List<Cache.Piste>
    suspend fun saveCache(cache: Cache.Piste)
}