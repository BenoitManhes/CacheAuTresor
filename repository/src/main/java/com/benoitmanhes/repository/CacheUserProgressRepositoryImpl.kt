package com.benoitmanhes.repository

import com.benoitmanhes.domain.interfaces.localdatasource.CacheUserProgressLocaleDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.CacheUserProgressRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.model.CacheUserProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CacheUserProgressRepositoryImpl @Inject constructor(
    private val remoteDataSource: CacheUserProgressRemoteDataSource,
    private val localeDataSource: CacheUserProgressLocaleDataSource,
) : CacheUserProgressRepository {

    override fun getCacheUserProgressFlow(explorerId: String, cacheId: String): Flow<CacheUserProgress?> = flow {
        fetchCacheUserProgress(explorerId = explorerId, cacheId = cacheId)
        emitAll(localeDataSource.getCacheUserProgressFlow(explorerId = explorerId, cacheId = cacheId))
    }

    override fun getAllCacheUserProgressFlow(explorerId: String): Flow<List<CacheUserProgress>> =
        localeDataSource.getAllUserProgressFlow(explorerId)

    override suspend fun getFetchedCacheUserProgress(explorerId: String, cacheId: String): CacheUserProgress? {
        fetchCacheUserProgress(explorerId = explorerId, cacheId = cacheId)
        return localeDataSource.getCacheUserProgress(explorerId = explorerId, cacheId = cacheId)
    }

    override suspend fun getAllUserProgressRemote(explorerId: String, remoteOnly: Boolean): List<CacheUserProgress> {
        val remoteData = remoteDataSource.getAllCacheUserProgress(explorerId)
        if (remoteOnly) {
            localeDataSource.saveCacheUserProgress(remoteData)
        }
        return remoteData
    }

    override suspend fun getAllUserProgressByCache(cacheId: String): List<CacheUserProgress> =
        remoteDataSource.getAllUserProgressByCache(cacheId = cacheId)

    override suspend fun saveCacheUserProgress(userProgress: CacheUserProgress) {
        remoteDataSource.saveCacheUserProgress(userProgress = userProgress)
        localeDataSource.saveCacheUserProgress(userProgress)
    }

    private suspend fun fetchCacheUserProgress(explorerId: String, cacheId: String) {
        val remoteObject = remoteDataSource.getCacheUserProgress(explorerId = explorerId, cacheId = cacheId)
        if (remoteObject == null) {
            localeDataSource.deleteCacheUserProgress(explorerId = explorerId, cacheId = cacheId)
        } else {
            localeDataSource.saveCacheUserProgress(remoteObject)
        }
    }
}
