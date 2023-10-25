package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.Explorer
import kotlinx.coroutines.flow.Flow

interface ExplorerRepository {
    suspend fun isExplorerNameAvailable(explorerName: String): Boolean
    suspend fun createUserExplorer(explorer: Explorer): Explorer
    suspend fun getUserExplorer(explorerId: String): Explorer
    fun getUserExplorerFlow(explorerId: String): Flow<Explorer>

    suspend fun deleteExplorer(explorerId: String)
    suspend fun getExplorer(explorerId: String): Explorer?
    suspend fun getExplorerFetched(explorerId: String): Explorer?
}
