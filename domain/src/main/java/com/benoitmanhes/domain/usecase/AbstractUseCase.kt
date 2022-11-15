package com.benoitmanhes.domain.usecase

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.core.error.CTStorageError
import com.benoitmanhes.core.result.CTResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

abstract class AbstractUseCase {

    fun <T> useCaseFlow(
        mapErr: (Throwable) -> CTDomainError = { defaultMapError(it) },
        block: suspend FlowCollector<CTResult<T>>.() -> Unit,
    ): Flow<CTResult<T>> = flow {
        block(this)
    }
        .onStart { emit(CTResult.Loading()) }
        .catch { t ->
            emit(CTResult.Failure(error = mapErr(t)))
        }

    protected fun defaultMapError(t: Throwable): CTDomainError {
        val code: CTDomainError.Code = when (t) {
            is CTDomainError -> t.code

            is CTRemoteError.AuthenticationInvalidCredentialError -> CTDomainError.Code.AUTHENTICATION_INVALID_CREDENTIAL
            is CTRemoteError.AuthenticationEmailInvalidForm -> CTDomainError.Code.AUTHENTICATION_EMAIL_INVALID_FORM
            is CTRemoteError.AuthenticationUserEmailNoExist -> CTDomainError.Code.AUTHENTICATION_USER_EMAIL_NO_EXIST
            is CTRemoteError.NetworkException -> CTDomainError.Code.NO_INTERNET
            is CTRemoteError.ParsingFailed,
            is CTRemoteError.ObjectNotFound -> CTDomainError.Code.SERVER_ERROR

            is CTStorageError.ExplorerNotFound -> CTDomainError.Code.EXPLORER_NOT_FOUND

            is CTStorageError.UnexpectedResult,
            is CTRemoteError.UnexpectedResult -> CTDomainError.Code.UNEXPECTED

            else -> CTDomainError.Code.UNKNOWN
        }
        return CTDomainError(code = code, cause = t, message = t.message ?: code.name)
    }
}
