package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.Explorer
import kotlinx.coroutines.flow.Flow

interface ExplorerRemoteDataSource {
    fun getExplorerFlow(explorerId: String): Flow<Explorer>
    suspend fun getExplorer(explorerId: String): Explorer
    suspend fun saveExplorer(explorer: Explorer)
    suspend fun isExplorerNameAvailable(name: String): Boolean
    suspend fun deleteExplorer(explorerId: String)
}
