package com.benoitmanhes.cacheautresor.screen.home.edit.pickname

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState

@Immutable
data class PickNameDraftCacheViewModelState(
    val nameValue: String? = null,
    val onTextChanged: (String) -> Unit,
    val bottomActionBar: BottomActionBarState,
)
