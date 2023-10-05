package com.benoitmanhes.domain.usecase.authentication

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.CTUseCaseImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    useCaseImpl: CTUseCaseImpl,
) : CTUseCase by useCaseImpl {
    operator fun invoke(): Flow<CTResult<Unit>> = useCaseFlow {
        authRepository.logout()
    }
}
