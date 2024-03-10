package com.benoitmanhes.common.compose.extensions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun Modifier.thenIf(
    condition: Boolean,
    modifier: @Composable Modifier.() -> Modifier,
): Modifier =
    then(
        if (condition) {
            modifier(this)
        } else {
            this
        }
    )

fun Modifier.surface(
    shape: Shape,
    backgroundColor: Color,
    border: BorderStroke? = null,
): Modifier = this
    .then(if (border != null) this.border(border, shape) else this)
    .background(color = backgroundColor, shape = shape)
    .clip(shape)
