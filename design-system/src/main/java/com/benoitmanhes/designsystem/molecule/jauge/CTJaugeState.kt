package com.benoitmanhes.designsystem.molecule.jauge

import androidx.compose.runtime.Immutable
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.utils.IconSpec

@Immutable
data class CTJaugeState(
    val rate: Float?,
    val icon: ComposeProvider<IconSpec>,
    val text: TextSpec?,
    val onClick: (() -> Unit)? = null,
)
