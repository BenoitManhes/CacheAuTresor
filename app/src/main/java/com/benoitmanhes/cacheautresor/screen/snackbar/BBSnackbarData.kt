package com.benoitmanhes.cacheautresor.screen.snackbar

import com.benoitmanhes.designsystem.utils.TextSpec
sealed interface BBSnackbarData {
    val message: TextSpec

    data class Info(override val message: TextSpec) : BBSnackbarData
    data class Error(override val message: TextSpec) : BBSnackbarData
}
