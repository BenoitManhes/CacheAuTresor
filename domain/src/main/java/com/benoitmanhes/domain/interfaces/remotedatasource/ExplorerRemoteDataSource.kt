package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.structure.BResult
import kotlinx.coroutines.flow.Flow

interface ExplorerRemoteDataSource {
    suspend fun saveExplorer(explorer: Explorer): BResult<Explorer>
    suspend fun isExplorerNameAvailable(name: String): BResult<Unit>
    fun getExplorerFlow(explorerId: String): Flow<BResult<Explorer>>
    suspend fun deleteExplorer(explorerId: String): BResult<Unit>
}