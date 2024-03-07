package com.benoitmanhes.core.extensions

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.core.result.CTSuspendResult

fun CTResult<*>.throwIfFailure() {
    (this as? CTResult.Failure)?.let { failure ->
        throw (failure.error ?: CTDomainError.Code.UNKNOWN.error())
    }
}

fun CTSuspendResult<*>.throwIfFailure() {
    (this as? CTSuspendResult.Failure)?.let { failure ->
        throw (failure.error ?: CTDomainError.Code.UNKNOWN.error())
    }
}
