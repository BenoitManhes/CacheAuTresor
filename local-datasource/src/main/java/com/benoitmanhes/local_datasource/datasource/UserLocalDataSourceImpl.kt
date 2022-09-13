package com.benoitmanhes.local_datasource.datasource

import com.benoitmanhes.domain.interfaces.datasource.UserLocalDataSource
import com.benoitmanhes.local_datasource.datastore.UserDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDataStore: UserDataStore,
) : UserLocalDataSource {

    override fun isAuthenticated(): Flow<Boolean?> = userDataStore.isAuthenticated()
    override suspend fun saveIsAuthenticated(value: Boolean) {
        userDataStore.saveIsAuthenticated(value)
    }
}