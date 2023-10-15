package com.benoitmanhes.cacheautresor.screen.modalbottomsheet

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ModalBottomSheetManager @Inject constructor() {
    private val _modalState = MutableStateFlow<ModalBottomSheetState?>(null)
    val modalState: StateFlow<ModalBottomSheetState?> get() = _modalState.asStateFlow()

    fun consumeModal() {
        _modalState.value = null
    }

    fun showModal(state: ModalBottomSheetState) {
        _modalState.value = state
    }
}
