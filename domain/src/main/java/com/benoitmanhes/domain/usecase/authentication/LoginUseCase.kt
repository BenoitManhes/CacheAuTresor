package com.benoitmanhes.domain.usecase.authentication

import com.benoitmanhes.domain.extension.convertResult
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.structure.BResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(
        identifier: String,
        password: String,
    ): Flow<BResult<Unit>> = authRepository.login(email = identifier, password = password).map {
        it.convertResult { }
    }
}
