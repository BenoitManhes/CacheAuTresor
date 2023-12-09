package com.benoitmanhes.domain.interfaces.localdatasource

import com.benoitmanhes.domain.model.CacheUserData
import kotlinx.coroutines.flow.Flow

interface CacheUserDataLocalDataSource {

    suspend fun getCacheUserData(cacheId: String): CacheUserData?
    fun getCacheUserDataFlow(cacheId: String): Flow<CacheUserData?>

    suspend fun saveCacheUserData(cacheUserData: CacheUserData)

    suspend fun deleteCacheUserData(cacheId: String)
    suspend fun clearCacheUserData()
}
