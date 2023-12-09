package com.benoitmanhes.repository

import com.benoitmanhes.domain.interfaces.localdatasource.SyncLocalDataSource
import com.benoitmanhes.domain.interfaces.repository.SyncRepository
import java.util.Date
import javax.inject.Inject

class SyncRepositoryImpl @Inject constructor(
    private val syncLocalDataSource: SyncLocalDataSource,
) : SyncRepository {
    override suspend fun needToSyncCache(): Boolean {
        val delay = Date().time - (syncLocalDataSource.getLastSyncCache()?.time ?: 0L)
        return delay > RepositoryConstants.Sync.cacheSyncDelay.inWholeMilliseconds
    }

    override suspend fun needToSyncUserPoints(): Boolean {
        val delay = Date().time - (syncLocalDataSource.getLastSyncUserPoints()?.time ?: 0L)
        return delay > RepositoryConstants.Sync.userPointsSyncDelay.inWholeMilliseconds
    }

    override suspend fun needToSyncAllUsers(): Boolean {
        val delay = Date().time - (syncLocalDataSource.getLastSyncAllUsers()?.time ?: 0L)
        return delay > RepositoryConstants.Sync.allUsersSyncDelay.inWholeMilliseconds
    }

    override suspend fun resetLastSyncCache() {
        syncLocalDataSource.setLastSyncCache(Date())
    }

    override suspend fun resetLastSyncUserPoints() {
        syncLocalDataSource.setLastSyncCache(Date())
    }

    override suspend fun resetLastSyncAllUsers() {
        syncLocalDataSource.setLastSyncAllUsers(Date())
    }

    override suspend fun clearLastSyncUserPoints() {
        syncLocalDataSource.setLastSyncCache(Date(0L))
    }
}
