package com.benoitmanhes.designsystem.molecule.button.fabbutton

import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec

data class FabButtonState(
    val icon: IconSpec,
    val onClick: () -> Unit,
    val text: TextSpec? = null,
    val type: FabButtonType = FabButtonType.COLORED,
)
