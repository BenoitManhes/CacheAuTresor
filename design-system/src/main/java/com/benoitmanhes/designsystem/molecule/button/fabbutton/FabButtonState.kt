package com.benoitmanhes.designsystem.molecule.button.fabbutton

import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec

data class FabButtonState(
    val icon: IconSpec,
    val onClick: () -> Unit,
    val text: TextSpec? = null,
    val type: FabButtonType = FabButtonType.COLORED,
)
