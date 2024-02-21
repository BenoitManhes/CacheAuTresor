package com.benoitmanhes.designsystem.utils.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun Modifier.ctClickable(
    onClick: (() -> Unit)?,
    enabled: Boolean = true,
    rippleColor: Color = CTTheme.color.ripple,
): Modifier =
    onClick?.let {
        this.clickable(
            onClick = onClick,
            enabled = enabled,
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(color = rippleColor),
        )
    } ?: this
