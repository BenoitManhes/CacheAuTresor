package com.benoitmanhes.domain.usecase

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.core.error.CTStorageError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.logger.CTLogger
import com.benoitmanhes.logger.e
import com.benoitmanhes.logger.i
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import org.slf4j.Logger

@Suppress("UnnecessaryAbstractClass")
abstract class AbstractUseCase {

    @Suppress("MemberVisibilityCanBePrivate")
    protected val logger: Logger = CTLogger.get(this::class.java.simpleName)

    fun <T> useCaseFlow(
        mapErr: (Throwable) -> CTDomainError = { defaultMapError(it) },
        block: suspend FlowCollector<CTResult<T>>.() -> Unit,
    ): Flow<CTResult<T>> = flow {
        block(this)
    }
        .onStart { emit(CTResult.Loading()) }
        .catch { t ->
            val domainError = mapErr(t)
            logError(domainError)
            emit(CTResult.Failure(error = domainError))
        }

    protected suspend fun <T> runCatch(
        mapErr: (Throwable) -> CTDomainError = { defaultMapError(it) },
        onError: (CTDomainError) -> T,
        block: suspend () -> T,
    ): T = try {
        block()
    } catch (t: Throwable) {
        val error = mapErr(t)
        logError(error)
        onError(error)
    }

    @Suppress("MemberVisibilityCanBePrivate")
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
