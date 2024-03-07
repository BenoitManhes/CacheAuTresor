package com.benoitmanhes.cacheautresor.screen.home.edit.pickunlockinstruction

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.screen.pickstring.PickStringUiState

@Immutable
data class PickUnlockInstructionsViewModelState(
    override val textValue: String? = null,
    override val onTextChanged: (String?) -> Unit,
    override val bottomActionBar: BottomActionBarState,
) : PickStringUiState
