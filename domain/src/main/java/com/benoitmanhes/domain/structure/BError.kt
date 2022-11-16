package com.benoitmanhes.domain.structure

import com.benoitmanhes.domain.interfaces.Model

sealed class BError(
    message: String?,
    cause: Throwable?,
) : Exception(
    message,
    cause,
),
    Model {

    data class AuthenticationError(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : BError(message, cause)

    object AuthenticationCodeInvalidError : BError(null, null)
    object ExplorerNameTakenError : BError(null, null)

    data class GetRemoteObjectError(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : BError(message, cause)

    object ObjectNotFound : BError(null, null)

    data class RemoteObjectEditingError(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : BError(message, cause)

    data class RemoteObjectParsingError(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : BError(message, cause)

    object UnexpectedResult : BError(null, null)

    data class UnknownError(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : BError(message, cause)
}
