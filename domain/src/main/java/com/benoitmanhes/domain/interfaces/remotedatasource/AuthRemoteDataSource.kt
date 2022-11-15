package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.model.Account
import com.benoitmanhes.domain.structure.BResult

interface AuthRemoteDataSource {
    fun getCurrentAccount(): Account?
    suspend fun login(email: String, password: String): Account
    suspend fun createAuthAccount(email: String, password: String, explorerId: String): Account
    suspend fun isAuthCodeValid(code: String): Boolean
    suspend fun deleteAuthCode(code: String)
    fun logout()
}
