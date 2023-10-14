package com.benoitmanhes.cacheautresor.screen.snackbar

import com.benoitmanhes.cacheautresor.error.localizedDescription
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.designsystem.utils.TextSpec

fun SnackbarManager.showError(error: CTDomainError) {
    showSnackBar(
        BBSnackbarData.Error(error.localizedDescription()),
    )
}

fun SnackbarManager.showInfo(message: TextSpec) {
    showSnackBar(
        BBSnackbarData.Info(message),
    )
}
