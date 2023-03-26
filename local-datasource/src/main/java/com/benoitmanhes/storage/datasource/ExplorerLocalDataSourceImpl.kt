package com.benoitmanhes.storage.datasource

import com.benoitmanhes.core.error.CTStorageError
import com.benoitmanhes.domain.interfaces.localdatasource.ExplorerLocalDataSource
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.storage.dao.ExplorerDao
import com.benoitmanhes.storage.model.roomModelConverter.RoomExplorerConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExplorerLocalDataSourceImpl @Inject constructor(
    private val explorerDao: ExplorerDao,
) : ExplorerLocalDataSource {

    override suspend fun saveExplorer(explorer: Explorer) {
        explorerDao.insert(RoomExplorerConverter.buildRoomModel(explorer))
    }

    override suspend fun getExplorer(explorerId: String): Explorer =
        explorerDao.findWithId(explorerId)?.let {
            it.toAppModel()
        } ?: throw CTStorageError.ExplorerNotFound

    override fun getExplorerFlow(explorerId: String): Flow<Explorer> =
        explorerDao.loadByName(explorerId).map {
            it.toAppModel()
        }

    override fun deleteExplorer(explorerId: String) {
        explorerDao.delete(explorerId)
    }
}
