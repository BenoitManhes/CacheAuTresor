package com.benoitmanhes.domain.usecase.authentication

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) : CTUseCase() {

    operator fun invoke(
        identifier: String,
        password: String,
    ): Flow<CTResult<Unit>> = useCaseFlow {
        authRepository.login(email = identifier, password = password)
        emit(CTResult.Success(Unit))
    }
}
