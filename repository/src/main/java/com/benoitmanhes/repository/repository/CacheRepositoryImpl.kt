package com.benoitmanhes.repository.repository

import com.benoitmanhes.domain.interfaces.remotedatasource.CacheRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.model.Cache
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(
    cacheRemoteDataSource: CacheRemoteDataSource,
) : CacheRepository {
    override suspend fun getAllCaches(): List<Cache> =
    override suspend fun saveCache(cache: Cache) =
}