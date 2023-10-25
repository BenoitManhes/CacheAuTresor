package com.benoitmanhes.repository

import com.benoitmanhes.domain.interfaces.localdatasource.CacheLocalDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.CacheRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.model.Cache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(
    private val cacheRemoteDataSource: CacheRemoteDataSource,
    private val cacheLocalDataSource: CacheLocalDataSource,
) : CacheRepository {
    override suspend fun getCache(cacheId: String): Cache? {
        val remoteCache = cacheRemoteDataSource.getCache(cacheId)
        remoteCache?.let { cacheLocalDataSource.saveCache(it) }
        return cacheLocalDataSource.getCache(cacheId)
    }

    override fun getCacheFlow(cacheId: String): Flow<Cache?> = flow {
        val remoteCache = cacheRemoteDataSource.getCache(cacheId)
        remoteCache?.let { cacheLocalDataSource.saveCache(it) }
        emitAll(cacheLocalDataSource.getCacheFlow(cacheId))
    }

    override fun getAllCachesFlow(): Flow<List<Cache>> =
        cacheLocalDataSource.getAllCachesFlow()

    override suspend fun fetchAllCaches() {
        val allCaches = cacheRemoteDataSource.getAllCaches()
        cacheLocalDataSource.saveCaches(allCaches)
    }

    override suspend fun saveCache(cache: Cache) {
        cacheRemoteDataSource.saveCache(cache)
        cacheLocalDataSource.saveCache(cache)
    }
}
