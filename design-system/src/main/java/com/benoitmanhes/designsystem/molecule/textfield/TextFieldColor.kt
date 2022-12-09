package com.benoitmanhes.designsystem.molecule.textfield

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
internal fun CTTextFieldColors(
    textColor: Color,
    color: Color,
): TextFieldColors = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    cursorColor = color,
    focusedLabelColor = color,
    focusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    backgroundColor = Color.Transparent,
)
