package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.CacheUserProgress
import kotlinx.coroutines.flow.Flow

interface CacheUserProgressRepository {
    suspend fun getCacheUserProgressFlow(explorerId: String, cacheId: String): Flow<CacheUserProgress?>
    suspend fun saveCacheUserProgress(userProgress: CacheUserProgress)
}
