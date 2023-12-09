package com.benoitmanhes.storage.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.SyncLocalDataSource
import com.benoitmanhes.storage.datastore.SyncDataStore
import java.util.Date
import javax.inject.Inject

class SyncLocalDataSourceImpl @Inject constructor(
    private val syncDataStore: SyncDataStore,
) : SyncLocalDataSource {
    override suspend fun getLastSyncCache(): Date? = syncDataStore.getLastSyncCaches()?.let { Date(it) }
    override suspend fun getLastSyncUserPoints(): Date? = syncDataStore.getLastSyncUserPoints()?.let { Date(it) }
    override suspend fun getLastSyncAllUsers(): Date? = syncDataStore.getLastSyncAllUsers()?.let { Date(it) }
    override suspend fun setLastSyncCache(date: Date): Unit = syncDataStore.saveLastSyncCache(date.time)
    override suspend fun setLastSyncUserPoints(date: Date): Unit = syncDataStore.saveLastSyncUserPoint(date.time)
    override suspend fun setLastSyncAllUsers(date: Date): Unit = syncDataStore.saveLastSyncAllUsers(date.time)
}
