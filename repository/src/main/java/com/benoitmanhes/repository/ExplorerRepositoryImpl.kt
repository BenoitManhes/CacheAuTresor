package com.benoitmanhes.repository

import com.benoitmanhes.core.error.CTRepositoryError
import com.benoitmanhes.domain.interfaces.localdatasource.ExplorerLocalDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
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
        return localDataSource.getExplorer(explorer.explorerId) ?: throw CTRepositoryError.UserExplorerNotFound
    }

    override suspend fun saveExplorer(explorer: Explorer, remoteOnly: Boolean) {
        remoteDataSource.saveExplorer(explorer)
        if (remoteOnly) return
        localDataSource.saveExplorer(explorer)
    }

    override suspend fun deleteExplorer(explorerId: String) {
        remoteDataSource.deleteExplorer(explorerId)
        localDataSource.deleteExplorer(explorerId)
    }

    override fun getUserExplorerFlow(explorerId: String): Flow<Explorer> = flow {
        localDataSource.getExplorer(explorerId)?.let { emit(it) }
        val remoteExplorer = remoteDataSource.getExplorer(explorerId) ?: throw CTRepositoryError.UserExplorerNotFound
        localDataSource.saveExplorer(remoteExplorer)
        emitAll(localDataSource.getExplorerFlow(explorerId))
    }

    override suspend fun getUserExplorer(explorerId: String): Explorer {
        val remoteExplorer = remoteDataSource.getExplorer(explorerId) ?: throw CTRepositoryError.UserExplorerNotFound
        localDataSource.saveExplorer(remoteExplorer)
        return localDataSource.getExplorer(explorerId) ?: throw CTRepositoryError.UserExplorerNotFound
    }

    override suspend fun getExplorer(explorerId: String): Explorer? =
        localDataSource.getExplorer(explorerId) ?: kotlin.run {
            val remoteExplorer = remoteDataSource.getExplorer(explorerId)
            remoteExplorer?.let { localDataSource.saveExplorer(it) }
            remoteExplorer
        }

    override suspend fun getExplorerFetched(explorerId: String, remoteOnly: Boolean): Explorer? {
        val remoteExplorer = remoteDataSource.getExplorer(explorerId)
        if (remoteOnly) return remoteExplorer
        remoteExplorer?.let { localDataSource.saveExplorer(it) }
        return localDataSource.getExplorer(explorerId)
    }
}
