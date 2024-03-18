package com.benoitmanhes.domain.usecase

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.error.CTError
import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.core.error.CTStorageError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.logger.CTLogger
import com.benoitmanhes.logger.e
import com.benoitmanhes.logger.i
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import org.slf4j.Logger

abstract class CTUseCase {

    @Suppress("MemberVisibilityCanBePrivate")
    protected val logger: Logger = CTLogger.get(this::class.java.simpleName)

    protected fun <T> useCaseFlow(
        mapErr: (CTError) -> CTDomainError = { defaultMapError(it) },
        block: suspend FlowCollector<CTResult<T>>.() -> Unit,
    ): Flow<CTResult<T>> = flow {
        block(this)
    }
        .onStart { emit(CTResult.Loading()) }
        .useCaseCatch(mapErr) { error ->
            CTResult.Failure(error = error)
        }

    protected fun <T> resultFlow(
        mapErr: (CTError) -> CTDomainError = { defaultMapError(it) },
        block: suspend () -> T,
    ): Flow<CTResult<T>> = useCaseFlow(mapErr) {
        emit(
            CTResult.Success(block())
        )
    }

    protected fun <T> Flow<T>.useCaseCatch(
        mapErr: (CTError) -> CTDomainError = { defaultMapError(it) },
        errorValue: (CTDomainError) -> T,
    ): Flow<T> = this
        .catch { t ->
            if (t is CTError) {
                val domainError = mapErr(t)
                logError(domainError)
                emit(errorValue(domainError))
            } else {
                logError(CTDomainError.Code.UNKNOWN.error(cause = t))
                throw t
            }
        }

    protected suspend fun <T> runCatch(
        mapErr: (CTError) -> CTDomainError = { defaultMapError(it) },
        onError: (CTDomainError) -> T,
        block: suspend () -> T,
    ): T = try {
        block()
    } catch (t: CTError) {
        val error = mapErr(t)
        logError(error)
        onError(error)
    }

    protected suspend fun runCatch(
        mapErr: (Throwable) -> CTDomainError = { defaultMapError(it) },
        block: suspend () -> Unit,
    ): Unit = runCatch(
        mapErr = mapErr,
        onError = {},
        block = block,
    )

    protected suspend fun <T> runCatchNullable(
        mapErr: (Throwable) -> CTDomainError = { defaultMapError(it) },
        onError: (CTDomainError) -> T? = { null },
        block: suspend () -> T,
    ): T? = runCatch(mapErr, onError, block)

    protected suspend fun <T> runCatchSuspendResult(
        mapErr: (Throwable) -> CTDomainError = { defaultMapError(it) },
        onError: (CTDomainError) -> CTSuspendResult<T> = { CTSuspendResult.Failure(it) },
        block: suspend () -> CTSuspendResult<T>,
    ): CTSuspendResult<T> = runCatch(mapErr, onError, block)

    protected fun defaultMapError(t: Throwable): CTDomainError {
        val code: CTDomainError.Code = when (t) {
            is CTDomainError -> t.code

            is CTRemoteError.AuthenticationInvalidCredentialError -> CTDomainError.Code.AUTHENTICATION_INVALID_CREDENTIAL
            is CTRemoteError.AuthenticationEmailInvalidForm -> CTDomainError.Code.AUTHENTICATION_EMAIL_INVALID_FORM
            is CTRemoteError.AuthenticationUserEmailNoExist -> CTDomainError.Code.AUTHENTICATION_USER_EMAIL_NO_EXIST
            is CTRemoteError.NetworkException -> CTDomainError.Code.NO_INTERNET
            is CTRemoteError.ParsingFailed,
            is CTRemoteError.ObjectNotFound,
            -> CTDomainError.Code.SERVER_ERROR

            is CTStorageError.ExplorerNotFound -> CTDomainError.Code.EXPLORER_NOT_FOUND

            is CTStorageError.UnexpectedResult,
            is CTRemoteError.UnexpectedResult,
            -> CTDomainError.Code.UNEXPECTED

            else -> CTDomainError.Code.UNKNOWN
        }
        return CTDomainError(code = code, cause = t, message = t.message ?: code.name)
    }

    private fun logError(error: CTDomainError) {
        if (error.code in nonImportantCodeErrors) {
            logger.i(t = error, message = error.message)
        } else {
            logger.e(t = error, message = error.message)
        }
    }

    private companion object {
        private val nonImportantCodeErrors = listOf(
            CTDomainError.Code.ACCOUNT_CREATION_EXPLORER_NAME_UNAVAILABLE,
            CTDomainError.Code.ACCOUNT_CREATION_INVALID_TOKEN,
            CTDomainError.Code.AUTHENTICATION_INVALID_CREDENTIAL,
            CTDomainError.Code.AUTHENTICATION_EMAIL_INVALID_FORM,
            CTDomainError.Code.AUTHENTICATION_USER_EMAIL_NO_EXIST,
            CTDomainError.Code.EXPLORER_NOT_FOUND,
            CTDomainError.Code.NO_INTERNET,
        )
    }
}
