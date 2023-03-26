package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getAuthAccountFlow(): Flow<Account?>
    suspend fun getAuthAccount(): Account?
    suspend fun login(email: String, password: String): Account
    suspend fun createAuthAccount(email: String, password: String, explorerId: String): Account
    suspend fun isAuthCodeValid(code: String): Boolean
    suspend fun deleteAuthCode(code: String): Unit
    suspend fun logout()
}
