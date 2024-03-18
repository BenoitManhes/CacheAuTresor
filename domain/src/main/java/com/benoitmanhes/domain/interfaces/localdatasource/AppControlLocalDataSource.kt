package com.benoitmanhes.domain.interfaces.localdatasource

import com.benoitmanhes.domain.model.AppControl
import kotlinx.coroutines.flow.Flow

interface AppControlLocalDataSource {
    suspend fun saveAppControl(value: AppControl)
    fun getAppControlFlow(): Flow<AppControl?>
}
