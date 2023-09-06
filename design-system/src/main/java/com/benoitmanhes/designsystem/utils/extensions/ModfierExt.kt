package com.benoitmanhes.designsystem.utils.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

fun Modifier.onClickNullable(
    onClick: (() -> Unit)?,
): Modifier =
    onClick?.let {
        this.clickable { onClick() }
    } ?: this
