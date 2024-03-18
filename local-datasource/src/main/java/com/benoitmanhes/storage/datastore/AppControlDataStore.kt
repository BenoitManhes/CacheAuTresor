package com.benoitmanhes.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.benoitmanhes.common.kotlin.extensions.nullIfBlank
import com.benoitmanhes.domain.model.AppControl
import com.benoitmanhes.storage.extension.editString
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppControlDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    suspend fun saveAppControl(value: AppControl) {
        val jsonValue = Gson().toJson(value)
        dataStore.editString(jsonValue, DataStoreKeys.appControl)
    }

    fun getAppControlFlow(): Flow<AppControl?> =
        dataStore.data.map { it[DataStoreKeys.appControl] }
            .distinctUntilChanged()
            .map { jsonValue ->
                jsonValue.nullIfBlank()?.let {
                    Gson().fromJson(jsonValue, AppControl::class.java)
                }
            }
}
