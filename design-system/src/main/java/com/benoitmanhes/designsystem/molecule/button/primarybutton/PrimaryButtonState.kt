package com.benoitmanhes.designsystem.molecule.button.primarybutton

import com.benoitmanhes.designsystem.utils.TextSpec

data class PrimaryButtonState(
    val text: TextSpec,
    val onClick: () -> Unit,
    val type: PrimaryButtonType = PrimaryButtonType.COLORED,
    val status: ButtonStatus = ButtonStatus.ENABLE,
    val options: Set<PrimaryButtonOption> = emptySet(),
)
