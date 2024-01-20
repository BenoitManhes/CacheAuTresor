package com.benoitmanhes.storage.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.CacheLocalDataSource
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.storage.dao.CacheDao
import com.benoitmanhes.storage.model.RoomCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CacheLocalDataSourceImpl @Inject constructor(
    private val cacheDao: CacheDao,
) : CacheLocalDataSource {
    override suspend fun getCache(cacheId: String): Cache? = withContext(Dispatchers.IO) {
        cacheDao.findWithId(cacheId)?.toAppModel()
    }

    override fun getCacheFlow(cacheId: String): Flow<Cache?> =
        cacheDao.findWithIdFlow(cacheId).map { it?.toAppModel() }

    override fun getAllCachesFlow(): Flow<List<Cache>> =
        cacheDao.findAllFlow().map { list -> list.map { it.toAppModel() } }

    override fun getAllCachesByExplorer(explorerId: String): Flow<List<Cache>> =
        cacheDao.findAllFlowByExplorer(explorerId).map { list -> list.map { it.toAppModel() } }

    override suspend fun saveCache(cache: Cache): Unit = withContext(Dispatchers.IO) {
        cacheDao.insert(RoomCache.build(cache))
    }

    override suspend fun saveCaches(caches: List<Cache>): Unit = withContext(Dispatchers.IO) {
        cacheDao.insert(caches.map { RoomCache.build(it) })
    }
}
