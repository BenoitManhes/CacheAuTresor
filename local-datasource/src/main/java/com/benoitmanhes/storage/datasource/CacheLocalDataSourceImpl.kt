package com.benoitmanhes.storage.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.CacheLocalDataSource
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.storage.dao.CacheDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CacheLocalDataSourceImpl @Inject constructor(
    private val classicalDao: CacheDao,
) : CacheLocalDataSource {

    override suspend fun saveCache(cache: Cache) {

    }

    override suspend fun getCache(cacheId: String): Cache {
        TODO("Not yet implemented")
    }

    override fun getCacheFlow(cache: Cache): Flow<Cache> {
        TODO("Not yet implemented")
    }

    override fun deleteCache(cacheId: String) {
        TODO("Not yet implemented")
    }
}