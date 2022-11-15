package com.benoitmanhes.repository.repository

import com.benoitmanhes.domain.interfaces.localdatasource.ExplorerLocalDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.structure.BResult

class ExplorerRepositoryImpl(
    private val localDataSource: ExplorerLocalDataSource,
    private val remoteDataSource: ExplorerRemoteDataSource,
) : ExplorerRepository {

    override suspend fun isExplorerNameAvailable(explorerName: String): Boolean =
        remoteDataSource.isExplorerNameAvailable(explorerName)

    override suspend fun createExplorer(explorer: Explorer): Explorer {
        remoteDataSource.saveExplorer(explorer)
        remoteDataSource.getExplorer(explorer.explorerId).also { remoteExplorer ->
            localDataSource.saveExplorer(remoteExplorer)
        }
        return localDataSource.getExplorer(explorer.explorerId)
    }

    override suspend fun deleteExplorer(explorerId: String) {
        remoteDataSource.deleteExplorer(explorerId)
        localDataSource.deleteExplorer(explorerId)
    }
}