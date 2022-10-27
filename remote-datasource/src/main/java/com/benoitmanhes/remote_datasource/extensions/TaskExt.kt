package com.benoitmanhes.remote_datasource.extensions

import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult
import com.google.android.gms.tasks.Task
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun <R, T> Task<R>.withCoroutine(
    onFailure: (Exception) -> BError,
    onSuccess: (R) -> BResult<T>,
): BResult<T> = suspendCoroutine { continuation ->
    this
        .addOnFailureListener { e ->
            continuation.resume(BResult.Failure(error = onFailure(e)))
        }
        .addOnSuccessListener { result ->
            continuation.resume(onSuccess(result))
        }

}
