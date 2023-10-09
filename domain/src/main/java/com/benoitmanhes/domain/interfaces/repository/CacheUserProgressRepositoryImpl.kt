package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.interfaces.localdatasource.CacheUserProgressLocaleDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.CacheUserProgressRemoteDataSource
import com.benoitmanhes.domain.model.CacheUserProgress
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CacheUserProgressRepositoryImpl @Inject constructor(
    private val remoteDataSource: CacheUserProgressRemoteDataSource,
    private val localeDataSource: CacheUserProgressLocaleDataSource,
) : CacheUserProgressRepository {

    override suspend fun getCacheUserProgressFlow(explorerId: String, cacheId: String): Flow<CacheUserProgress?> {
        val remoteObject = remoteDataSource.getCacheUserProgress(explorerId = explorerId, cacheId = cacheId)
        if (remoteObject == null) {
            localeDataSource.deleteCacheUserProgress(explorerId = explorerId, cacheId = cacheId)
        } else {
            localeDataSource.saveCacheUserProgress(remoteObject)
        }
        return localeDataSource.getCacheUserProgressFlow(explorerId = explorerId, cacheId = cacheId)
    }

    override suspend fun saveCacheUserProgress(userProgress: CacheUserProgress): Unit =
        remoteDataSource.saveCacheUserProgress(userProgress = userProgress)
}
