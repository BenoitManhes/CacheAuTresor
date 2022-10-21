package com.benoitmanhes.domain.interfaces.localdatasource

import com.benoitmanhes.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveAccount(account: Account)
    suspend fun removeAccount()
    suspend fun getAccount(): Account?
    fun getAccountFlow(): Flow<Account?>
}
