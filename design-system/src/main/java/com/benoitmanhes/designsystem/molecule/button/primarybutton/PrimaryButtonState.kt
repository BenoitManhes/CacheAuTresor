package com.benoitmanhes.designsystem.molecule.button.primarybutton

import androidx.compose.runtime.Stable
import com.benoitmanhes.designsystem.utils.TextSpec

@Stable
data class PrimaryButtonState(
    val text: TextSpec,
    val onClick: () -> Unit,
    val type: PrimaryButtonType = PrimaryButtonType.COLORED,
    val status: ButtonStatus = ButtonStatus.ENABLE,
    val options: Set<PrimaryButtonOption> = emptySet(),
)
