package com.benoitmanhes.remote_datasource.extensions

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun <R> Task<R>.withCoroutine(
    onFailure: (Exception) -> Throwable = { it.toCTError() },
): R = suspendCoroutine { continuation ->
    this
        .addOnFailureListener { e ->
            continuation.resumeWithException(onFailure(e))
        }
        .addOnSuccessListener {
            continuation.resume(it as R)
        }
}
