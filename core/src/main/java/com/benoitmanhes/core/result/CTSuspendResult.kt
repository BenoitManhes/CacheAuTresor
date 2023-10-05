package com.benoitmanhes.core.result

import com.benoitmanhes.core.error.CTDomainError

sealed class CTSuspendResult<out T> {

    data class Success<out T>(val successData: T) : CTSuspendResult<T>()
    data class Failure<out T>(val error: CTDomainError? = null, val failureData: T? = null) : CTSuspendResult<T>()

    val data: T?
        get() {
            return when (this) {
                is Success -> successData
                is Failure -> failureData
            }
        }
}
