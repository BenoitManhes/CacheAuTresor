package com.benoitmanhes.cacheautresor.screen.alertdialog

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlertDialogManager @Inject constructor() {

    private val _dialogState = MutableStateFlow<AlertDialogState?>(null)
    val dialogState: StateFlow<AlertDialogState?> get() = _dialogState.asStateFlow()

    fun showDialog(data: AlertDialogState) {
        _dialogState.value = data
    }

    fun closeDialog() {
        _dialogState.value = null
    }
}
