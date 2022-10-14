package com.benoitmanhes.domain.usecase.authentication

import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke() {
        authRepository.logout()
    }
}