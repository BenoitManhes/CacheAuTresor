package com.benoitmanhes.domain.extension

import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult

fun <T> BResult.Failure<Any?>.convert(): BResult.Failure<T> = BResult.Failure(error, null)
fun <T> BResult.Loading<Any?>.convert(): BResult.Loading<T> = BResult.Loading(null, progress)

fun <T, U> BResult<T>.convertResult(
    convertError: ((BError?) -> BResult<U>)? = null,
    convertData: ((T) -> U),
): BResult<U> = when (this) {
    is BResult.Loading -> BResult.Loading(partialData?.let(convertData), progress)
    is BResult.Success -> BResult.Success(convertData(this.successData))
    is BResult.Failure -> convertError?.invoke(this.error) ?: BResult.Failure(error)
}