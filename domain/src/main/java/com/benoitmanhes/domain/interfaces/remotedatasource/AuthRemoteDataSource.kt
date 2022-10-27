package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.Account
import com.benoitmanhes.domain.structure.BResult

interface AuthRemoteDataSource {
    fun getCurrentAccount(): Account?
    suspend fun login(email: String, password: String): BResult<Account>
    suspend fun createAuthAccount(email: String, password: String, explorerId: String): BResult<Account>
    suspend fun isAuthCodeValid(code: String): BResult<Unit>
    suspend fun deleteAuthCode(code: String): BResult<Unit>
    fun logout()
}
