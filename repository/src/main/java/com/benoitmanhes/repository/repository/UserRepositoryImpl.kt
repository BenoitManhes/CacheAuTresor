package com.benoitmanhes.repository.repository

import com.benoitmanhes.domain.interfaces.datasource.UserLocalDataSource
import com.benoitmanhes.domain.interfaces.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
) : UserRepository {

    override fun isAuthenticated(): Flow<Boolean?> =
        userLocalDataSource.isAuthenticated()

    override suspend fun saveIsAuthenticated(value: Boolean) {
        userLocalDataSource.saveIsAuthenticated(value)
    }
}