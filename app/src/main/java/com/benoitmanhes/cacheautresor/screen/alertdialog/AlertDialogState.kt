package com.benoitmanhes.cacheautresor.screen.alertdialog

import androidx.compose.runtime.Composable
import com.benoitmanhes.designsystem.molecule.alertdialog.AlertDialogAction

interface AlertDialogState {

    val actions: List<AlertDialogAction>
    val onDismiss: (() -> Unit)?

    @Composable
    fun Content(closeDialog: () -> Unit)
}
