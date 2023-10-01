package com.benoitmanhes.designsystem.utils.extensions

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

fun Modifier.onClickNullable(
    onClick: (() -> Unit)?,
): Modifier =
    onClick?.let {
        this.clickable { onClick() }
    } ?: this
