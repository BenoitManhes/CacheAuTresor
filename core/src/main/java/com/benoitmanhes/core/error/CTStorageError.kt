package com.benoitmanhes.core.error

data class CTStorageError(
    override val message: String,
    override val cause: Throwable? = null,
) : CTError(message, cause) {

    object ExplorerNotFound : CTRemoteError(null, "Explorer not found")
    data class UnexpectedResult(override val message: String) : CTRemoteError(null, message)
}
