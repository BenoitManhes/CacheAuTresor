package com.benoitmanhes.core.error

sealed class CTRepositoryError(
    override val cause: Throwable? = null,
    override val message: String? = null,
) : CTError(message, cause) {

    object UserExplorerNotFound : CTRepositoryError()
    class UnexpectedResult(override val message: String? = null) : CTRepositoryError(message = message)
}
