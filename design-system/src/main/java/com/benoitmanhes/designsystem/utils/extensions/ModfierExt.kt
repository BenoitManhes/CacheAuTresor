package com.benoitmanhes.designsystem.utils.extensions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
    border: BorderStroke? = null,
    onClick: (() -> Unit)? = null,
): Modifier = this
    .then(if (border != null) Modifier.border(border, shape) else Modifier)
    .background(color = backgroundColor, shape = shape)
    .ctClickable(onClick)
    .clip(shape)
