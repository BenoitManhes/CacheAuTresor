package com.benoitmanhes.local_datasource.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    val emailAccount: Preferences.Key<String> = stringPreferencesKey("emailAccount")
    val explorerId: Preferences.Key<String> = stringPreferencesKey("explorerId")
}