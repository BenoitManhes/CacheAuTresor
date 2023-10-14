package com.benoitmanhes.cacheautresor.screen.snackbar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SnackbarViewModel @Inject constructor(
    private val snackbarManager: SnackbarManager,
) : ViewModel() {

    val shownSnackBar: StateFlow<BBSnackbarData?> = snackbarManager.shownSnackbar

    fun consumeSnackBar(): Unit = snackbarManager.consumeSnackBar()
}
