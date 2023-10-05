package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.CacheUserData
import kotlinx.coroutines.flow.Flow

interface CacheUserDataRepository {
    suspend fun getCacheUserData(cacheId: String): CacheUserData?
    suspend fun getCacheUserDataFlow(cacheId: String): Flow<CacheUserData?>
    suspend fun saveCacheUserData(userData: CacheUserData)
}
