package com.benoitmanhes.core.extensions

import com.benoitmanhes.core.error.CTDomainError

fun CTDomainError.Code.error(
    message: String = this.name,
    cause: Throwable? = null,
): CTDomainError = CTDomainError(code = this, message = message, cause = cause)
