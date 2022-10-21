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

    override suspend fun isExplorerNameAvailable(explorerName: String): BResult<Unit> =
        remoteDataSource.isExplorerNameAvailable(explorerName)

    override suspend fun createExplorer(explorer: Explorer): BResult<Explorer> {
        val remoteResult = remoteDataSource.saveExplorer(explorer)
        return remoteResult.data?.let { explorerSaved ->
            localDataSource.saveExplorer(explorerSaved)
            localDataSource.getExplorer(explorerSaved.explorerId)
        } ?: remoteResult
    }

    override suspend fun deleteExplorer(explorerId: String): BResult<Unit> {
        val remoteResult = remoteDataSource.deleteExplorer(explorerId)
        if (remoteResult is BResult.Success) {
            localDataSource.deleteExplorer(explorerId)
        }
        return remoteResult
    }
}