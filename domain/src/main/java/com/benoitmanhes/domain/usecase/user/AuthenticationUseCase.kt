package com.benoitmanhes.domain.usecase.user

import com.benoitmanhes.domain.interfaces.repository.UserRepository
import com.benoitmanhes.domain.utils.BResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        identifier: String?,
        password: String?,
    ): Flow<BResult<Unit>> = flow {
        emit(BResult.Loading())
        delay(3000)
        userRepository.saveIsAuthenticated(true)
        emit(BResult.Success(Unit))
    }
}