package com.benoitmanhes.designsystem.utils.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.benoitmanhes.designsystem.theme.CTTheme

fun Modifier.ctClickable(
    onClick: (() -> Unit)?,
    enabled: Boolean = true,
    rippleColor: Color? = null,
): Modifier =
    onClick?.let {
        this.composed {
            clickable(
                onClick = onClick,
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = rippleColor ?: CTTheme.color.ripple,
                ),
            )
        }
    } ?: this

fun Modifier.ctSurface(
    shape: Shape,
    backgroundColor: Color,
    elevation: Dp = 0.dp,
    onClick: (() -> Unit)? = null,
): Modifier = this
    .shadow(elevation, shape, clip = false)
    .background(color = backgroundColor, shape = shape)
    .ctClickable(onClick)
    .clip(shape)
