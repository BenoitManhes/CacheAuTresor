package com.benoitmanhes.storage.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.AppControlLocalDataSource
import com.benoitmanhes.domain.model.AppControl
import com.benoitmanhes.storage.datastore.AppControlDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppControlLocalDataSourceImpl @Inject constructor(
    private val appControlDataStore: AppControlDataStore,
) : AppControlLocalDataSource {
    override suspend fun saveAppControl(value: AppControl) {
        appControlDataStore.saveAppControl(value)
    }

    override fun getAppControlFlow(): Flow<AppControl?> =
        appControlDataStore.getAppControlFlow()
}
