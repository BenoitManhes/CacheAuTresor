package com.benoitmanhes.storage.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.ExplorerLocalDataSource
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.storage.dao.ExplorerDao
import com.benoitmanhes.storage.model.roomModelConverter.RoomExplorerConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExplorerLocalDataSourceImpl @Inject constructor(
    private val explorerDao: ExplorerDao,
) : ExplorerLocalDataSource {

    override suspend fun saveExplorer(explorer: Explorer): Unit = withContext(Dispatchers.IO) {
        explorerDao.insert(RoomExplorerConverter.buildRoomModel(explorer))
    }

    override suspend fun getExplorer(explorerId: String): Explorer? = withContext(Dispatchers.IO) {
        explorerDao.findWithId(explorerId)?.toAppModel()
    }

    override fun getExplorerFlow(explorerId: String): Flow<Explorer> =
        explorerDao.loadByName(explorerId)
            .map { it.toAppModel() }
            .flowOn(Dispatchers.IO)

    override suspend fun deleteExplorer(explorerId: String): Unit = withContext(Dispatchers.IO) {
        explorerDao.delete(explorerId)
    }
}
