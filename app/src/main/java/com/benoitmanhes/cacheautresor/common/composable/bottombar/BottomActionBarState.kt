package com.benoitmanhes.cacheautresor.common.composable.bottombar

import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState

data class BottomActionBarState(
    val firstButtonState: PrimaryButtonState,
    val secondButtonState: PrimaryButtonState? = null,
)
