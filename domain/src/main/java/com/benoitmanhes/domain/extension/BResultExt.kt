package com.benoitmanhes.domain.extension

import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult

fun <T, U> BResult.Failure<U>.convert(): BResult.Failure<T> = BResult.Failure(error, null)
fun <T, U> BResult.Loading<U>.convert(): BResult.Loading<T> = BResult.Loading(null, progress)

fun <T, U> BResult<T>.convertResult(
    convertError: ((BError?) -> BResult<U>)? = null,
    convertData: ((T) -> U),
): BResult<U> = when (this) {
    is BResult.Loading -> BResult.Loading(partialData?.let(convertData), progress)
    is BResult.Success -> BResult.Success(convertData(this.successData))
    is BResult.Failure -> convertError?.invoke(this.error) ?: BResult.Failure(error)
}

suspend fun <T, U> BResult<T>.suspendConvertResult(
    convertError: ((BError?) -> BResult<U>)? = null,
    convertData: suspend ((T) -> U),
): BResult<U> = when (this) {
    is BResult.Loading -> BResult.Loading(partialData?.let { convertData(it) }, progress)
    is BResult.Success -> BResult.Success(convertData(this.successData))
    is BResult.Failure -> convertError?.invoke(this.error) ?: BResult.Failure(error)
}
