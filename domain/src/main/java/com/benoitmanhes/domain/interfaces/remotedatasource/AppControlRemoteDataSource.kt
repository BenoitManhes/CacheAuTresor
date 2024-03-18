package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.AppControl

interface AppControlRemoteDataSource {
    suspend fun fetchAppControl(): AppControl?
}
