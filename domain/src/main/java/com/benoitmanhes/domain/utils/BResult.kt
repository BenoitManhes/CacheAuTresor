package com.benoitmanhes.domain.utils

sealed class BResult<out T> {

    data class Loading<out T>(val partialData: T? = null, val progress: Float? = null) : BResult<T>()
    data class Success<out T>(val successData: T) : BResult<T>()
    data class Failure<out T>(val throwable: Throwable? = null, val failureData: T? = null) : BResult<T>() {

        @Suppress("unused")
        constructor(message: String) : this(throwable = Exception(message))
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