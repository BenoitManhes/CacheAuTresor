package com.benoitmanhes.repository.repository

import com.benoitmanhes.domain.interfaces.localdatasource.AuthLocalDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.AuthRemoteDataSource
import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult
import com.benoitmanhes.domain.extension.convert
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.model.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AuthRepositoryImpl(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override fun getAuthAccount(): Flow<Account?> {
        CoroutineScope(Dispatchers.IO).launch {
            authRemoteDataSource.getCurrentUserAccount()?.let { account ->
                authLocalDataSource.saveAccount(account)
            } ?: run { logout() }
        }
        return authLocalDataSource.getAccountFlow()
    }

    override fun login(email: String, password: String): Flow<BResult<Account>> =
        authRemoteDataSource.login(email = email, password).fetchAccount()

    override fun createAuthAccount(email: String, password: String): Flow<BResult<Account>> =
        authRemoteDataSource.createAuthAccount(email = email, password = password).fetchAccount()

    override fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            authRemoteDataSource.logout()
            authLocalDataSource.removeAccount()
        }
    }

    override fun getAuthCode(code: String): Flow<BResult<Unit>> = authRemoteDataSource.getAuthCode(code)

    override fun deleteAuthCode(code: String) {
        authRemoteDataSource.deleteAuthCode(code)
    }

    private fun Flow<BResult<Account>>.fetchAccount(): Flow<BResult<Account>> = flatMapMerge { authResult ->
        when (authResult) {
            is BResult.Loading -> flowOf(authResult.convert())
            is BResult.Failure -> flowOf(authResult.convert())
            is BResult.Success -> {
                authLocalDataSource.saveAccount(authResult.successData)
                authLocalDataSource.getAccountFlow().map { localAccount ->
                    if (localAccount != null) {
                        BResult.Success(localAccount)
                    } else {
                        BResult.Failure(BError.UnexpectedResult)
                    }
                }
            }
        }
    }
}
