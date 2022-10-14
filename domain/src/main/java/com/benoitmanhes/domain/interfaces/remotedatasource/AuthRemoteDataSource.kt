package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.Account
import com.benoitmanhes.domain.structure.BResult
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {
    fun getCurrentUserAccount(): Account?
    fun createAuthAccount(email: String, password: String): Flow<BResult<Account>>
    fun login(email: String, password: String): Flow<BResult<Account>>
    fun logout()
    fun getAuthCode(code: String): Flow<BResult<Unit>>
    fun deleteAuthCode(code: String)
}