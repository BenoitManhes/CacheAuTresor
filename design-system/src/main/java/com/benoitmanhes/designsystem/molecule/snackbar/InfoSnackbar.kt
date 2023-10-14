package com.benoitmanhes.designsystem.molecule.snackbar

import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.runtime.Composable
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun InfoSnackbar(
    snackbarData: SnackbarData,
) {
    Snackbar(
        snackbarData = snackbarData,
        shape = CTTheme.shape.small,
    )
}
