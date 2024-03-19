package com.benoitmanhes.core.error

data class CTDomainError(
    val code: Code,
    override val message: String = code.name,
    override val cause: Throwable? = null,
) : CTError(message, cause) {
    enum class Code {
        ACCOUNT_CREATION_EXPLORER_NAME_UNAVAILABLE,
        ACCOUNT_CREATION_INVALID_TOKEN,
        AUTHENTICATION_INVALID_PASSWORD,
        AUTHENTICATION_USER_EMAIL_NO_EXIST,
        AUTHENTICATION_EMAIL_INVALID_FORM,
        AUTHENTICATION_CREDENTIAL_INVALID,
        CREW_MEMBER_NAME_UNAVAILABLE,
        EXPLORER_NOT_FOUND,
        DRAFT_CACHE_MISSING_FIELD,
        DRAFT_CACHE_MISSING_STEP,
        DRAFT_CACHE_STEP_INCOMPLETE,
        DRAFT_CACHE_MISSING_CREW_MEMBER,
        DRAFT_CACHE_INIT_COORDINATES_INVALID,
        DRAFT_CACHE_FINAL_COORDINATES_INVALID,
        CACHE_INVALID_LOG_CODE,
        CACHE_INVALID_UNLOCK_CODE,
        CACHE_NOT_FOUND,
        CACHE_STEP_NOT_FOUND,
        CACHE_USER_PROGRESS_NOT_FOUND,
        INVALID_COORDINATES,
        NO_AUTHENTICATION,
        NO_INTERNET,
        SERVER_ERROR,
        UNEXPECTED,
        UNKNOWN,
    }
}
