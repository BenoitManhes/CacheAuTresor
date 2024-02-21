package com.benoitmanhes.designsystem.molecule.jauge

import androidx.compose.runtime.Stable
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.ComposeProvider

@Stable
data class CTJaugeState(
    val rate: Float?,
    val icon: ComposeProvider<IconSpec>,
    val text: TextSpec?,
)
