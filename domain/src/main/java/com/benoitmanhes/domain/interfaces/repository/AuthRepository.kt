package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.Account
import com.benoitmanhes.domain.structure.BResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getAuthAccount(): Flow<Account?>
    fun login(email: String, password: String): Flow<BResult<Account>>
    fun createAuthAccount(email: String, password: String): Flow<BResult<Account>>
    fun logout()
    fun getAuthCode(code: String): Flow<BResult<Unit>>
    fun deleteAuthCode(code: String)
}