package com.benoitmanhes.core.result

import com.benoitmanhes.core.error.CTDomainError

sealed class CTResult<out T> {

    data class Loading<out T>(val partialData: T? = null, val progress: Float? = null) : CTResult<T>()
    data class Success<out T>(val successData: T) : CTResult<T>()
    data class Failure<out T>(val error: CTDomainError? = null, val failureData: T? = null) : CTResult<T>() {
    }

    val data: T?
        get() {
            return when (this) {
                is Loading -> partialData
                is Success -> successData
                is Failure -> failureData
            }
        }
}
