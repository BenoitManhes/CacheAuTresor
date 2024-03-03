package com.benoitmanhes.designsystem.molecule.snackbar

import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.runtime.Composable
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun ErrorSnackbar(
    snackbarData: SnackbarData,
) {
    Snackbar(
        snackbarData = snackbarData,
        backgroundColor = CTTheme.color.surfaceCritical,
        actionColor = CTTheme.color.textOnSurfaceCritical,
        shape = CTTheme.shape.small,
    )
}
