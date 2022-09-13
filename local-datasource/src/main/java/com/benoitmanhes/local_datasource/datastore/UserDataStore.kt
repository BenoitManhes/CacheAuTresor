package com.benoitmanhes.local_datasource.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    suspend fun saveIsAuthenticated(value: Boolean) {
        dataStore.edit { it[DataStoreKeys.isAuthenticated] = value }
    }

    fun isAuthenticated(): Flow<Boolean?> = dataStore.data.map { it[DataStoreKeys.isAuthenticated] }
}