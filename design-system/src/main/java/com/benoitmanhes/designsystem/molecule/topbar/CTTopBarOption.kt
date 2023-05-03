package com.benoitmanhes.designsystem.molecule.topbar

import com.benoitmanhes.designsystem.utils.IconSpec

sealed interface CTTopBarOption {

    data class NavIcon(
        val icon: IconSpec,
        val onClick: () -> Unit,
    ) : CTTopBarOption

    data class ActionIcon(
        val icon: IconSpec,
        val onClick: () -> Unit,
    ) : CTTopBarOption
}
