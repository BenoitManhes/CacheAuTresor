package com.benoitmanhes.domain.usecase.authentication

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckAuthCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) : CTUseCase() {

    operator fun invoke(code: String): Flow<CTResult<String>> = useCaseFlow {
        if (authRepository.isAuthCodeValid(code)) {
            emit(CTResult.Success(code))
        } else {
            throw CTDomainError(CTDomainError.Code.ACCOUNT_CREATION_INVALID_TOKEN)
        }
    }
}
