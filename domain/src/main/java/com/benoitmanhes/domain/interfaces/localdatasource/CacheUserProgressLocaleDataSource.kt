package com.benoitmanhes.domain.interfaces.localdatasource

import com.benoitmanhes.domain.model.CacheUserProgress
import kotlinx.coroutines.flow.Flow

interface CacheUserProgressLocaleDataSource {
    fun getCacheUserProgressFlow(cacheId: String, explorerId: String): Flow<CacheUserProgress?>
    suspend fun getCacheUserProgress(cacheId: String, explorerId: String): CacheUserProgress?
    suspend fun saveCacheUserProgress(userProgress: CacheUserProgress)
    suspend fun deleteCacheUserProgress(cacheId: String, explorerId: String)
}
