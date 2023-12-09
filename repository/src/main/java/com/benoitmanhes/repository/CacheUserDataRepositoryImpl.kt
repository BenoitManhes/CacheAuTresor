package com.benoitmanhes.repository

import com.benoitmanhes.domain.interfaces.localdatasource.CacheUserDataLocalDataSource
import com.benoitmanhes.domain.interfaces.repository.CacheUserDataRepository
import com.benoitmanhes.domain.model.CacheUserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CacheUserDataRepositoryImpl @Inject constructor(
    private val cacheUserDataLocalDataSource: CacheUserDataLocalDataSource,
) : CacheUserDataRepository {

    override suspend fun getCacheUserData(cacheId: String): CacheUserData? =
        cacheUserDataLocalDataSource.getCacheUserData(cacheId)

    override suspend fun getCacheUserDataFlow(cacheId: String): Flow<CacheUserData?> =
        cacheUserDataLocalDataSource.getCacheUserDataFlow(cacheId)

    override suspend fun saveCacheUserData(userData: CacheUserData): Unit =
        cacheUserDataLocalDataSource.saveCacheUserData(userData)

    override suspend fun clearCacheUserData() {
        cacheUserDataLocalDataSource.clearCacheUserData()
    }
}
