package com.benoitmanhes.cacheautresor.screen.alertdialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AlertDialogView(
    alertDialogViewModel: AlertDialogViewModel = hiltViewModel(),
) {
    val alertDialogState by alertDialogViewModel.dialogState.collectAsStateWithLifecycle()

    alertDialogState?.let { _state ->
        _state.Content(
            closeDialog = {
                alertDialogViewModel.closeDialog()
            }
        )
    }
}
