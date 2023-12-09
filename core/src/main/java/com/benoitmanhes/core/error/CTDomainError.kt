package com.benoitmanhes.core.error

data class CTDomainError(
    val code: Code,
    override val message: String = code.name,
    override val cause: Throwable? = null,
) : CTError(message, cause) {
    enum class Code {
        ACCOUNT_CREATION_EXPLORER_NAME_UNAVAILABLE,
        ACCOUNT_CREATION_INVALID_TOKEN,
        AUTHENTICATION_INVALID_CREDENTIAL,
        AUTHENTICATION_USER_EMAIL_NO_EXIST,
        AUTHENTICATION_EMAIL_INVALID_FORM,
        EXPLORER_NOT_FOUND,
        CACHE_INVALID_LOG_CODE,
        CACHE_INVALID_UNLOCK_CODE,
        CACHE_NOT_FOUND,
        CACHE_STEP_NOT_FOUND,
        CACHE_USER_PROGRESS_NOT_FOUND,
        NO_AUTHENTICATION,
        NO_INTERNET,
        SERVER_ERROR,
        UNEXPECTED,
        UNKNOWN,
    }
}
