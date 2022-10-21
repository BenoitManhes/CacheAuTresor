package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.structure.BResult

interface ExplorerRepository {
    suspend fun isExplorerNameAvailable(explorerName: String): BResult<Unit>
    suspend fun createExplorer(explorer: Explorer): BResult<Explorer>
    suspend fun deleteExplorer(explorerId: String): BResult<Unit>
}