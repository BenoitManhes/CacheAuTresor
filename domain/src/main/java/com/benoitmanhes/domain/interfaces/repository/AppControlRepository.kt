package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.AppControl
import kotlinx.coroutines.flow.Flow

interface AppControlRepository {
    suspend fun fetchAppControl()
    fun getAppControlAsFlow(): Flow<AppControl?>
}
