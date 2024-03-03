package com.benoitmanhes.common.compose.extensions

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.thenIf(
    condition: Boolean,
    modifier: @Composable Modifier.() -> Modifier,
): Modifier =
    then(
        if (condition) {
            this.composed { modifier(this) }
        } else {
            this
        }
    )

fun Modifier.surface(
    shape: Shape,
    backgroundColor: Color,
    border: BorderStroke? = null,
): Modifier = this
    .then(if (border != null) Modifier.border(border, shape) else Modifier)
    .background(color = backgroundColor, shape = shape)
    .clip(shape)
