package com.benoitmanhes.cacheautresor.screen.snackbar

import com.benoitmanhes.cacheautresor.error.localizedDescription
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.result.CTResult

fun SnackbarManager.showError(error: CTDomainError?) {
    error?.let {
        showSnackBar(
            BBSnackbarData.Error(error.localizedDescription()),
        )
    }
}

fun SnackbarManager.showOnFailure(result: CTResult<*>) {
    showError((result as? CTResult.Failure)?.error)
}

fun SnackbarManager.showInfo(message: TextSpec) {
    showSnackBar(
        BBSnackbarData.Info(message),
    )
}
