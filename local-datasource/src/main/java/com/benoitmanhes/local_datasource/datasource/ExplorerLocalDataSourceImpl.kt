package com.benoitmanhes.local_datasource.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.ExplorerLocalDataSource
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult
import com.benoitmanhes.local_datasource.dao.ExplorerDao
import com.benoitmanhes.local_datasource.model.roomModelConverter.RoomExplorerConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExplorerLocalDataSourceImpl @Inject constructor(
    private val explorerDao: ExplorerDao,
) : ExplorerLocalDataSource {

    override suspend fun saveExplorer(explorer: Explorer) {
        explorerDao.insert(RoomExplorerConverter.buildRoomModel(explorer))
    }

    override suspend fun getExplorer(explorerId: String): BResult<Explorer> =
        explorerDao.findWithId(explorerId)?.let {
            BResult.Success(it.toAppModel())
        } ?: BResult.Failure(BError.ObjectNotFound)

    override fun getExplorerFlow(explorer: Explorer): Flow<BResult<Explorer>> =
        explorerDao.loadByName(explorer.explorerId).map {
            BResult.Success(it.toAppModel())
        }

    override fun deleteExplorer(explorerId: String) {
        explorerDao.delete(explorerId)
    }
}
