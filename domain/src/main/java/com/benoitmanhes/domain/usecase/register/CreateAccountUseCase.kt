package com.benoitmanhes.domain.usecase.register

import com.benoitmanhes.domain.extension.convert
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(
        tokenAccount: String,
        email: String,
        password: String,
    ): Flow<BResult<Unit>> = flow {
        emit(BResult.Loading())
        handleResult(authRepository.isAuthCodeValid(tokenAccount)) {
            handleResult(authRepository.createAuthAccount(email = email, password = password)) {
                emit(authRepository.deleteAuthCode(tokenAccount))
            }
        }
    }
}

private suspend fun <R, U> FlowCollector<BResult<U>>.handleResult(
    result: BResult<R>,
    onError: suspend FlowCollector<BResult<U>>.(BResult.Failure<R>) -> Unit = { failure ->
        emit(failure.convert())
    },
    onSuccess: suspend FlowCollector<BResult<U>>.(BResult.Success<R>) -> Unit,
) {
    when (result) {
        is BResult.Failure -> onError(result)
        is BResult.Success -> onSuccess(result)
        else -> {
            onError(BResult.Failure(BError.UnexpectedResult))
        }
    }
}
