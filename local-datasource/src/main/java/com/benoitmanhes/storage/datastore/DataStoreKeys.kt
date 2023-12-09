package com.benoitmanhes.storage.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    val emailAccount: Preferences.Key<String> = stringPreferencesKey("emailAccount")
    val explorerId: Preferences.Key<String> = stringPreferencesKey("explorerId")
    val lastSyncCacheTimestamp: Preferences.Key<Long> = longPreferencesKey("last-sync-cache")
    val lastSyncUserPointTimestamp: Preferences.Key<Long> = longPreferencesKey("last-sync-user-point")
    val lastSyncUsersTimestamp: Preferences.Key<Long> = longPreferencesKey("last-sync-users")
}
