package com.benoitmanhes.cacheautresor.screen.home.edit.pickunlockcode

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.screen.pickstring.PickStringUiState

@Immutable
data class PickUnlockCodeViewModelState(
    override val textValue: String? = null,
    override val onTextChanged: (String?) -> Unit,
    override val bottomActionBar: BottomActionBarState,
) : PickStringUiState
