package com.benoitmanhes.domain.usecase.common

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import javax.inject.Inject

class GetMyExplorerIdUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(): String = authRepository.getAuthAccount()?.explorerId ?: throw CTDomainError(
        CTDomainError.Code.NO_AUTHENTICATION
    )
}
