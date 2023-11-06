package com.benoitmanhes.designsystem.molecule.jauge

import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec

data class CTJaugeState(
    val rate: Float?,
    val icon: IconSpec,
    val text: TextSpec?,
)
