package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.CacheUserProgress

interface CacheUserProgressRemoteDataSource {
    suspend fun getCacheUserProgress(explorerId: String, cacheId: String): CacheUserProgress?
    suspend fun saveCacheUserProgress(userProgress: CacheUserProgress)
    suspend fun getAllCacheUserProgress(explorerId: String): List<CacheUserProgress>
}
