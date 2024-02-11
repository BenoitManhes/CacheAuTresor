package com.benoitmanhes.cacheautresor.screen.home.edit.picktype

import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.designsystem.molecule.card.CTSelectionCardState
import javax.annotation.concurrent.Immutable

@Immutable
data class PickTypeDraftCacheViewModelState(
    val bottomActionBar: BottomActionBarState,
    val typeSelectionCards: Map<String, CTSelectionCardState>,
)
