package com.benoitmanhes.repository

import com.benoitmanhes.core.error.CTRepositoryError
import com.benoitmanhes.domain.interfaces.localdatasource.ExplorerLocalDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExplorerRepositoryImpl @Inject constructor(
    private val localDataSource: ExplorerLocalDataSource,
    private val remoteDataSource: ExplorerRemoteDataSource,
) : ExplorerRepository {

    override suspend fun isExplorerNameAvailable(explorerName: String): Boolean =
        remoteDataSource.isExplorerNameAvailable(explorerName)

    override suspend fun createUserExplorer(explorer: Explorer): Explorer {
        remoteDataSource.saveExplorer(explorer)
        remoteDataSource.getExplorer(explorer.explorerId).also { remoteExplorer ->
            if (remoteExplorer == null) throw CTRepositoryError.UnexpectedResult()
            localDataSource.saveExplorer(remoteExplorer)
        }
        return localDataSource.getExplorer(explorer.explorerId)
    }

    override suspend fun deleteExplorer(explorerId: String) {
        remoteDataSource.deleteExplorer(explorerId)
        localDataSource.deleteExplorer(explorerId)
    }

    override suspend fun getUserExplorerFlow(explorerId: String): Flow<Explorer> {
        val remoteExplorer = remoteDataSource.getExplorer(explorerId) ?: throw CTRepositoryError.UserExplorerNotFound
        localDataSource.saveExplorer(remoteExplorer)
        return localDataSource.getExplorerFlow(explorerId)
    }

    override suspend fun getUserExplorer(explorerId: String): Explorer {
        val remoteExplorer = remoteDataSource.getExplorer(explorerId) ?: throw CTRepositoryError.UserExplorerNotFound
        localDataSource.saveExplorer(remoteExplorer)
        return localDataSource.getExplorer(explorerId)
    }

    override suspend fun getExplorer(explorerId: String): Explorer? = remoteDataSource.getExplorer(explorerId)
}
