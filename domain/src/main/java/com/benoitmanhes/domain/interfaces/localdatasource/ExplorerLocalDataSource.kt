package com.benoitmanhes.domain.interfaces.localdatasource

import com.benoitmanhes.domain.model.Explorer
import kotlinx.coroutines.flow.Flow

interface ExplorerLocalDataSource {
    suspend fun saveExplorer(explorer: Explorer)
    suspend fun getExplorer(explorerId: String): Explorer
    fun getExplorerFlow(explorerId: String): Flow<Explorer>
    fun deleteExplorer(explorerId: String)
}
