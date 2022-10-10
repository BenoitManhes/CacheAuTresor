package com.benoitmanhes.domain.structure

sealed class BResult<out T> {

    data class Loading<out T>(val partialData: T? = null, val progress: Float? = null) : BResult<T>()
    data class Success<out T>(val successData: T) : BResult<T>()
    data class Failure<out T>(val error: BError? = null, val failureData: T? = null) : BResult<T>() {

        @Suppress("unused")
        constructor(message: String) : this(error = BError.UnknownError(message = message))
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