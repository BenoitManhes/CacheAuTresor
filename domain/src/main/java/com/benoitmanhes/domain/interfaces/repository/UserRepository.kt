package com.benoitmanhes.domain.interfaces.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun isAuthenticated(): Flow<Boolean?>
    suspend fun saveIsAuthenticated(value: Boolean)
}