package com.benoitmanhes.domain.interfaces.localdatasource

import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.structure.BResult
import kotlinx.coroutines.flow.Flow

interface ExplorerLocalDataSource {
    suspend fun saveExplorer(explorer: Explorer)
    suspend fun getExplorer(explorerId: String): BResult<Explorer>
    fun getExplorerFlow(explorer: Explorer): Flow<BResult<Explorer>>
    fun deleteExplorer(explorerId: String)
}