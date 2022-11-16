package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.Explorer

interface ExplorerRepository {
    suspend fun isExplorerNameAvailable(explorerName: String): Boolean
    suspend fun createExplorer(explorer: Explorer): Explorer
    suspend fun deleteExplorer(explorerId: String)
}
