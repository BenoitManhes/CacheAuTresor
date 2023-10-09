package com.benoitmanhes.storage.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.CacheUserProgressLocaleDataSource
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.storage.dao.CacheUserProgressDao
import com.benoitmanhes.storage.model.roomModelConverter.RoomCacheUserProgressConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CacheUserProgressLocaleDataSourceImpl @Inject constructor(
    private val cacheUserProgressDao: CacheUserProgressDao,
) : CacheUserProgressLocaleDataSource {

    override fun getCacheUserProgressFlow(cacheId: String, explorerId: String): Flow<CacheUserProgress?> =
        cacheUserProgressDao.findWithIdFlow(explorerId = explorerId, cacheId = cacheId).map { it?.toAppModel() }

    override suspend fun saveCacheUserProgress(userProgress: CacheUserProgress): Unit =
        cacheUserProgressDao.insert(RoomCacheUserProgressConverter.buildRoomModel(userProgress))

    override suspend fun deleteCacheUserProgress(cacheId: String, explorerId: String) {
        cacheUserProgressDao.delete(cacheId = cacheId, explorerId = explorerId)
    }
}
