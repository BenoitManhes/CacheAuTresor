package com.benoitmanhes.cacheautresor.screen.home.edit.picksize

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.designsystem.molecule.card.CTSelectionCardState
import com.benoitmanhes.domain.model.CacheSize

@Immutable
data class PickSizeViewModelState(
    val bottomActionBar: BottomActionBarState? = null,
    val sizeSelection: Map<CacheSize, CTSelectionCardState> = emptyMap(),
) {
    val sizeSelected: CacheSize?
        get() = sizeSelection.filterValues { it.isSelected }
            .firstNotNullOfOrNull { it.key }
}
