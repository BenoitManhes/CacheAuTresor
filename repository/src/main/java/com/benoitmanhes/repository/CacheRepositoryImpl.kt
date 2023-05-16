package com.benoitmanhes.repository

import com.benoitmanhes.domain.interfaces.remotedatasource.CacheRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.model.Cache
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(
    private val cacheRemoteDataSource: CacheRemoteDataSource,
) : CacheRepository {
    override suspend fun getCache(cacheId: String): Cache? =
        cacheRemoteDataSource.getCache(cacheId)

    override suspend fun getAllCaches(): List<Cache> =
        cacheRemoteDataSource.getAllCaches()

    override suspend fun saveCache(cache: Cache): Unit =
        cacheRemoteDataSource.saveCache(cache)
}
