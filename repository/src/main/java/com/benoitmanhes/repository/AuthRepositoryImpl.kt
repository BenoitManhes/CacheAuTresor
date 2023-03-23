package com.benoitmanhes.repository

import com.benoitmanhes.core.error.CTStorageError
import com.benoitmanhes.domain.interfaces.localdatasource.AuthLocalDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.AuthRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.model.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override fun getAuthAccount(): Flow<Account?> {
        CoroutineScope(Dispatchers.IO).launch {
            authRemoteDataSource.getCurrentAccount()?.let { account ->
                authLocalDataSource.saveAccount(account)
            } ?: run { logout() }
        }
        return authLocalDataSource.getAccountFlow()
    }

    override suspend fun login(email: String, password: String): Account {
        val loginAccount = authRemoteDataSource.login(email = email, password)
        return fetchAccount(loginAccount)
    }

    override suspend fun createAuthAccount(email: String, password: String, explorerId: String): Account {
        val createdAccount = authRemoteDataSource.createAuthAccount(email = email, password = password, explorerId = explorerId)
        return fetchAccount(createdAccount)
    }

    override suspend fun logout() {
        authRemoteDataSource.logout()
        authLocalDataSource.removeAccount()
    }

    override suspend fun isAuthCodeValid(code: String): Boolean = authRemoteDataSource.isAuthCodeValid(code)

    override suspend fun deleteAuthCode(code: String): Unit = authRemoteDataSource.deleteAuthCode(code)

    private suspend fun fetchAccount(account: Account): Account {
        authLocalDataSource.saveAccount(account)
        return authLocalDataSource.getAccount() ?: throw CTStorageError.UnexpectedResult("No account after saving")
    }
}
