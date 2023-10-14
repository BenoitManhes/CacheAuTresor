package com.benoitmanhes.cacheautresor.screen.snackbar

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SnackbarManager @Inject constructor() {
    private val _shownSnackbar: MutableStateFlow<BBSnackbarData?> = MutableStateFlow(null)
    val shownSnackbar: StateFlow<BBSnackbarData?> = _shownSnackbar.asStateFlow()

    fun showSnackBar(data: BBSnackbarData) {
        _shownSnackbar.value = data
    }

    fun consumeSnackBar() {
        _shownSnackbar.value = null
    }
}
