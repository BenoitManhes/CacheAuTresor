package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.Account
import com.benoitmanhes.domain.structure.BResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getAuthAccount(): Flow<Account?>
    fun login(email: String, password: String): Flow<BResult<Account>>
    suspend fun createAuthAccount(email: String, password: String, explorerId: String): BResult<Account>
    suspend fun isAuthCodeValid(code: String): BResult<Unit>
    suspend fun deleteAuthCode(code: String): BResult<Unit>
    fun logout()
}