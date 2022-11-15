package com.benoitmanhes.core.error

data class CTDomainError(
    val code: Code,
    override val message: String = code.name,
    override val cause: Throwable? = null,
) : CTError(message, cause) {
    enum class Code {
        ACCOUNT_CREATION_INVALID_TOKEN,
        ACCOUNT_CREATION_EXPLORER_NAME_UNAVAILABLE,
        AUTHENTICATION_INVALID_CREDENTIAL,
        AUTHENTICATION_USER_EMAIL_NO_EXIST,
        AUTHENTICATION_EMAIL_INVALID_FORM,
        EXPLORER_NOT_FOUND,
        NO_INTERNET,
        SERVER_ERROR,
        UNEXPECTED,
        UNKNOWN,
    }
}
