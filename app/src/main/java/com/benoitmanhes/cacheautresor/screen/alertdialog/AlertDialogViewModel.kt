package com.benoitmanhes.cacheautresor.screen.alertdialog

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AlertDialogViewModel @Inject constructor(
    private val alertDialogManager: AlertDialogManager,
) : ViewModel() {

    val dialogState: StateFlow<AlertDialogState?> = alertDialogManager.dialogState

    fun closeDialog(): Unit = alertDialogManager.closeDialog()
}
