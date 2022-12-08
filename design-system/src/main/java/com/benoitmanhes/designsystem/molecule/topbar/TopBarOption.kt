package com.benoitmanhes.designsystem.molecule.topbar

import com.benoitmanhes.designsystem.utils.IconSpec

sealed interface TopBarOption {

    data class NavIcon(
        val icon: IconSpec,
        val onClick: () -> Unit,
    ) : TopBarOption

    data class ActionIcon(
        val icon: IconSpec,
        val onClick: () -> Unit,
    ) : TopBarOption
}