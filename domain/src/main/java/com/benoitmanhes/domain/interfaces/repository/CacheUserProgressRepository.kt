package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.CacheUserProgress
import kotlinx.coroutines.flow.Flow

interface CacheUserProgressRepository {
    fun getCacheUserProgressFlow(explorerId: String, cacheId: String): Flow<CacheUserProgress?>
    fun getAllCacheUserProgressFlow(explorerId: String): Flow<List<CacheUserProgress>>
    suspend fun getFetchedCacheUserProgress(explorerId: String, cacheId: String): CacheUserProgress?
    suspend fun saveCacheUserProgress(userProgress: CacheUserProgress)
    suspend fun fetchedAllUserProgress(explorerId: String)
}
