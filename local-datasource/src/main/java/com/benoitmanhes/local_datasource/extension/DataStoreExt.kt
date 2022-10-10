package com.benoitmanhes.local_datasource.extension

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

suspend fun DataStore<Preferences>.editString(value: String?, keyPref: Preferences.Key<String>) {
    this.edit { pref ->
        value?.let {
            pref[keyPref] = value
        } ?: kotlin.run {
            pref.remove(keyPref)
        }
    }
}