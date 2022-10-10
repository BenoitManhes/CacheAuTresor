package com.benoitmanhes.local_datasource.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.AuthLocalDataSource
import com.benoitmanhes.domain.model.Account
import com.benoitmanhes.local_datasource.datastore.AuthDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val authDataStore: AuthDataStore,
) : AuthLocalDataSource {

    override fun getAccountFlow(): Flow<Account?> = authDataStore.getAccountFlow()
    override suspend fun saveAccount(account: Account) {
        authDataStore.saveAccount(account)
    }

    override suspend fun removeAccount() = authDataStore.removeAccount()
}