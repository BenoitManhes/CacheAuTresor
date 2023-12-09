package com.benoitmanhes.cacheautresor.screen.modalbottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable

interface ModalBottomSheetState {
    val onDismiss: () -> Unit
    val option: Set<ModalBottomSheetOption>

    @Composable
    fun Content(scope: ColumnScope, hide: () -> Unit)
}
