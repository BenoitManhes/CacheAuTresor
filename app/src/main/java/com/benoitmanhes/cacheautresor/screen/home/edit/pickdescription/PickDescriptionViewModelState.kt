package com.benoitmanhes.cacheautresor.screen.home.edit.pickdescription

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState

@Immutable
data class PickDescriptionViewModelState(
    val descriptionValue: String? = null,
    val onTextChanged: (String?) -> Unit,
    val bottomActionBar: BottomActionBarState,
)
