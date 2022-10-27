package com.benoitmanhes.local_datasource.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.benoitmanhes.domain.model.Account
import com.benoitmanhes.local_datasource.extension.editString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    suspend fun saveAccount(value: Account) {
        dataStore.editString(value.email, DataStoreKeys.emailAccount)
        dataStore.editString(value.explorerId, DataStoreKeys.explorerId)
    }

    suspend fun removeAccount() {
        dataStore.edit { pref ->
            pref.remove(DataStoreKeys.emailAccount)
            pref.remove(DataStoreKeys.explorerId)
        }
    }

    fun getAccountFlow(): Flow<Account?> =
        combine(
            dataStore.data.map { it[DataStoreKeys.emailAccount] },
            dataStore.data.map { it[DataStoreKeys.explorerId] },
        ) { email, explorerId ->
            email?.let { safeEmail ->
                explorerId?.let { safeId ->
                    Account(explorerId = safeId, email = safeEmail)
                }
            }
        }
}
