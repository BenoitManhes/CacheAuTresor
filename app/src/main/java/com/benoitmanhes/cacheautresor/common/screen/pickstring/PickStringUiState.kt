package com.benoitmanhes.cacheautresor.common.screen.pickstring

import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState

interface PickStringUiState {
    val textValue: String?
    val onTextChanged: (String?) -> Unit
    val bottomActionBar: BottomActionBarState
}
