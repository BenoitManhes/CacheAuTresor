package com.benoitmanhes.storage.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.CacheUserDataLocalDataSource
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.storage.dao.CacheUserDataDao
import com.benoitmanhes.storage.model.roomModelConverter.RoomCacheUserDataConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CacheUserDataLocalDataSourceImpl @Inject constructor(
    private val cacheUserDataDao: CacheUserDataDao,
) : CacheUserDataLocalDataSource {

    override suspend fun getCacheUserData(cacheId: String): CacheUserData? = withContext(Dispatchers.IO) {
        cacheUserDataDao.findWithId(cacheId)?.toAppModel()
    }

    override fun getCacheUserDataFlow(cacheId: String): Flow<CacheUserData?> =
        cacheUserDataDao.findWithIdFlow(cacheId)
            .map { it?.toAppModel() }
            .flowOn(Dispatchers.IO)

    override suspend fun saveCacheUserData(cacheUserData: CacheUserData): Unit = withContext(Dispatchers.IO) {
        cacheUserDataDao.insert(RoomCacheUserDataConverter.buildRoomModel(cacheUserData))
    }

    override suspend fun deleteCacheUserData(cacheId: String): Unit = withContext(Dispatchers.IO) {
        cacheUserDataDao.delete(cacheId)
    }
}
