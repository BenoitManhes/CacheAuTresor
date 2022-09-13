package com.benoitmanhes.local_datasource.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

object DataStoreKeys {
    val isAuthenticated: Preferences.Key<Boolean> = booleanPreferencesKey("isAuthenticated")
}