package com.benoitmanhes.core.error

sealed class CTRemoteError(
    override val cause: Throwable? = null,
    override val message: String,
) : CTError(message, cause) {

    data class AuthenticationInvalidCredentialError(override val cause: Throwable?) : CTRemoteError(
        cause,
        "Invalid password"
    )

    data class AuthenticationEmailInvalidForm(override val cause: Throwable?) : CTRemoteError(
        cause,
        "User email not found"
    )

    data class AuthenticationUserEmailNoExist(override val cause: Throwable?) : CTRemoteError(
        cause,
        "User email not found"
    )

    data class NetworkException(override val cause: Throwable?) : CTRemoteError(cause, "Check your internet connection")
    data class ObjectNotFound(override val message: String) : CTRemoteError(null, message)
    data class ParsingFailed(override val message: String) : CTRemoteError(null, message)
    data class UnexpectedResult(override val message: String) : CTRemoteError(null, message)
    data class Unknown(
        override val cause: Throwable?,
        override val message: String = "Unknown remote error",
    ) : CTRemoteError(cause, message)
}
