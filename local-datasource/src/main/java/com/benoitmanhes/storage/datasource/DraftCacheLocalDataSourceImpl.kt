package com.benoitmanhes.storage.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.DraftCacheLocalDataSource
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.storage.dao.DraftCacheDao
import com.benoitmanhes.storage.model.RoomDraftCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DraftCacheLocalDataSourceImpl @Inject constructor(
    private val draftCacheDao: DraftCacheDao,
) : DraftCacheLocalDataSource {

    override suspend fun getDraftCache(draftCacheId: String): DraftCache? = withContext(Dispatchers.IO) {
        draftCacheDao.findWithId(draftCacheId)?.toAppModel()
    }

    override fun getDraftCacheFlow(draftCacheId: String): Flow<DraftCache?> =
        draftCacheDao.findWithIdFlow(draftCacheId).map { it.toAppModel() }

    override suspend fun deleteDraftCache(draftCacheId: String): Unit = withContext(Dispatchers.IO) {
        draftCacheDao.delete(draftCacheId)
    }

    override suspend fun clearAll(): Unit = withContext(Dispatchers.IO) {
        draftCacheDao.deleteAll()
    }

    override suspend fun saveDraftCache(draftCache: DraftCache): Unit = withContext(Dispatchers.IO) {
        draftCacheDao.insert(RoomDraftCache.build(draftCache))
    }

    override fun getAllDraftCacheFlow(): Flow<List<DraftCache>> =
        draftCacheDao.findAllFlow().map { list -> list.map { it.toAppModel() } }
}
