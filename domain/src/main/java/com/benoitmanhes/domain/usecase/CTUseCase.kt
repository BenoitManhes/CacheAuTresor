package com.benoitmanhes.domain.usecase

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.core.result.CTSuspendResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

interface CTUseCase {
    fun <T> useCaseFlow(
        mapErr: (Throwable) -> CTDomainError = { defaultMapError(it) },
        block: suspend FlowCollector<CTResult<T>>.() -> Unit,
    ): Flow<CTResult<T>>

    suspend fun <T> runCatch(
        mapErr: (Throwable) -> CTDomainError = { defaultMapError(it) },
        onError: (CTDomainError) -> T,
        block: suspend () -> T,
    ): T

    suspend fun <T> runCatchSuspendResult(
        mapErr: (Throwable) -> CTDomainError = { defaultMapError(it) },
        onError: (CTDomainError) -> CTSuspendResult<T> = { CTSuspendResult.Failure(it) },
        block: suspend () -> CTSuspendResult<T>,
    ): CTSuspendResult<T>

    fun defaultMapError(t: Throwable): CTDomainError
}
