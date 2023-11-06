package com.benoitmanhes.cacheautresor.screen.home.profile

import com.benoitmanhes.cacheautresor.screen.home.profile.section.ExplorerCardState
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState

data class ProfileViewModelState(
    val explorerCard: ExplorerCardState,
    val logoutButton: PrimaryButtonState,
)
