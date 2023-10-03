package com.benoitmanhes.storage.datasource

import com.benoitmanhes.core.error.CTStorageError
import com.benoitmanhes.domain.interfaces.localdatasource.CacheUserDataLocalDataSource
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.storage.dao.CacheUserDataDao
import com.benoitmanhes.storage.model.roomModelConverter.RoomCacheUserDataConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CacheUserDataLocalDataSourceImpl @Inject constructor(
    private val cacheUserDataDao: CacheUserDataDao,
) : CacheUserDataLocalDataSource {

    override suspend fun getCacheUserData(cacheId: String): CacheUserData =
        cacheUserDataDao.findWithId(cacheId)?.let {
            it.toAppModel()
        } ?: throw CTStorageError.CacheUserDataNotFound

    override fun getCacheUserDataFlow(cacheId: String): Flow<CacheUserData> =
        cacheUserDataDao.findWithIdFlow(cacheId).map { it.toAppModel() }

    override suspend fun saveCacheUserData(cacheUserData: CacheUserData) {
        cacheUserDataDao.insert(RoomCacheUserDataConverter.buildRoomModel(cacheUserData))
    }

    override suspend fun deleteCacheUserData(cacheId: String) {
        cacheUserDataDao.delete(cacheId)
    }
}