package com.benoitmanhes.storage.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.CacheUserProgressLocaleDataSource
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.storage.dao.CacheUserProgressDao
import com.benoitmanhes.storage.model.roomModelConverter.RoomCacheUserProgressConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CacheUserProgressLocaleDataSourceImpl @Inject constructor(
    private val cacheUserProgressDao: CacheUserProgressDao,
) : CacheUserProgressLocaleDataSource {

    override fun getCacheUserProgressFlow(cacheId: String, explorerId: String): Flow<CacheUserProgress?> =
        cacheUserProgressDao
            .findWithIdFlow(explorerId = explorerId, cacheId = cacheId)
            .map { it?.toAppModel() }
            .flowOn(Dispatchers.IO)

    override fun getAllUserProgressFlow(explorerId: String): Flow<List<CacheUserProgress>> =
        cacheUserProgressDao
            .findAllForExplorer(explorerId = explorerId)
            .mapNotNull { list -> list.map { it.toAppModel() } }
            .flowOn(Dispatchers.IO)

    override suspend fun getCacheUserProgress(cacheId: String, explorerId: String): CacheUserProgress? = withContext(
        Dispatchers.IO
    ) {
        cacheUserProgressDao.findWithId(cacheId = cacheId, explorerId = explorerId)?.toAppModel()
    }

    override suspend fun saveCacheUserProgress(userProgress: CacheUserProgress): Unit = withContext(Dispatchers.IO) {
        cacheUserProgressDao.insert(RoomCacheUserProgressConverter.buildRoomModel(userProgress))
    }

    override suspend fun saveCacheUserProgress(userProgressList: List<CacheUserProgress>): Unit = withContext(
        Dispatchers.IO
    ) {
        cacheUserProgressDao.insert(
            userProgressList.map(RoomCacheUserProgressConverter::buildRoomModel)
        )
    }

    override suspend fun deleteCacheUserProgress(cacheId: String, explorerId: String): Unit = withContext(
        Dispatchers.IO
    ) {
        cacheUserProgressDao.delete(cacheId = cacheId, explorerId = explorerId)
    }
}
