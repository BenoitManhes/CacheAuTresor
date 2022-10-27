package com.benoitmanhes.repository.repository

import com.benoitmanhes.domain.extension.convert
import com.benoitmanhes.domain.interfaces.localdatasource.AuthLocalDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.AuthRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.model.Account
import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class AuthRepositoryImpl(
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

    override fun login(email: String, password: String): Flow<BResult<Account>> = flow {
        emit(BResult.Loading())
        emit(authRemoteDataSource.login(email = email, password).fetchAccount())
    }

    override suspend fun createAuthAccount(email: String, password: String, explorerId: String): BResult<Account> =
        authRemoteDataSource.createAuthAccount(email = email, password = password, explorerId = explorerId).fetchAccount()

    override fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            authRemoteDataSource.logout()
            authLocalDataSource.removeAccount()
        }
    }

    override suspend fun isAuthCodeValid(code: String): BResult<Unit> = authRemoteDataSource.isAuthCodeValid(code)

    override suspend fun deleteAuthCode(code: String): BResult<Unit> =
        authRemoteDataSource.deleteAuthCode(code)

    private suspend fun BResult<Account>.fetchAccount(): BResult<Account> =
        when (this) {
            is BResult.Loading -> this.convert()
            is BResult.Failure -> this.convert()
            is BResult.Success -> {
                authLocalDataSource.saveAccount(this.successData)
                authLocalDataSource.getAccount()?.let { account ->
                    BResult.Success(account)
                } ?: BResult.Failure(BError.UnexpectedResult)
            }
        }
}
