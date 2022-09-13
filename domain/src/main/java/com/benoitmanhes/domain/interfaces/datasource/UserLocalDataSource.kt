package com.benoitmanhes.domain.interfaces.datasource

import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun saveIsAuthenticated(value: Boolean)
    fun isAuthenticated(): Flow<Boolean?>
}