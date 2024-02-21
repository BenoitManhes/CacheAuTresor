package com.benoitmanhes.cacheautresor.screen.home.edit.picktype

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.designsystem.molecule.card.CTSelectionCardState

@Immutable
data class PickTypeDraftCacheViewModelState(
    val bottomActionBar: BottomActionBarState,
    val typeSelectionCards: Map<String, CTSelectionCardState>,
)
