package com.benoitmanhes.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.benoitmanhes.storage.extension.editLong
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SyncDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun saveLastSyncCache(timestamp: Long) {
        dataStore.editLong(timestamp, DataStoreKeys.lastSyncCacheTimestamp)
    }

    suspend fun saveLastSyncUserPoint(timestamp: Long) {
        dataStore.editLong(timestamp, DataStoreKeys.lastSyncUserPointTimestamp)
    }

    suspend fun getLastSyncCaches(): Long? =
        dataStore.data.map { it[DataStoreKeys.lastSyncCacheTimestamp] }.first()

    suspend fun getLastSyncUserPoints(): Long? =
        dataStore.data.map { it[DataStoreKeys.lastSyncUserPointTimestamp] }.first()
}
