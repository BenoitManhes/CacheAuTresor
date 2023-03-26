package com.benoitmanhes.core.error

sealed class CTError(
    message: String?,
    cause: Throwable?,
) : Exception(message, cause)
