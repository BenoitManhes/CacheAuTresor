package com.benoitmanhes.cacheautresor.screen.modalbottomsheet

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ModalBottomSheetViewModel @Inject constructor(
    private val modalBottomSheetManager: ModalBottomSheetManager,
) : ViewModel() {
    val modalState: StateFlow<ModalBottomSheetState?> = modalBottomSheetManager.modalState

    fun consumeModal(): Unit = modalBottomSheetManager.consumeModal()
}
