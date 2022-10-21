package com.benoitmanhes.domain.usecase.authentication

import com.benoitmanhes.domain.extension.convertResult
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.structure.BResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckAuthCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(code: String): Flow<BResult<String>> = flow {
        emit(BResult.Loading())
        emit(authRepository.isAuthCodeValid(code).convertResult { code })
    }
}
