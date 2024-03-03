package com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed

object CommonModalAction {
    fun finallyNo(onClick: () -> Unit = {}): PrimaryButtonState = PrimaryButtonState(
        text = TextSpec.Resources(R.string.common_finallyNo),
        onClick = onClick,
    )

    fun delete(onClick: () -> Unit): PrimaryButtonState = PrimaryButtonState(
        text = TextSpec.Resources(R.string.common_delete),
        gradientBackground = CTTheme.composed { color.gradientSurfaceCritical },
        onClick = onClick,
    )

    fun validate(onClick: () -> Unit): PrimaryButtonState = PrimaryButtonState(
        text = TextSpec.Resources(R.string.common_validate),
        onClick = onClick,
    )
}
