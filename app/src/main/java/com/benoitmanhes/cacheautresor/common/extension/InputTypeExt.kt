package com.benoitmanhes.cacheautresor.common.extension

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.benoitmanhes.cacheautresor.common.utils.InputType

fun InputType.getKeyboardOption(hasNext: Boolean): KeyboardOptions {
    val imeAction: ImeAction = if (hasNext) ImeAction.Next else ImeAction.Done
    return when (this) {
        InputType.Default -> KeyboardOptions.Default.copy(
            imeAction = imeAction,
        )
        InputType.Email -> KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Email,
            imeAction = imeAction,
        )
        InputType.Number -> KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Number,
            imeAction = imeAction,
        )
        InputType.Password -> KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            // Avoid animation glitch due to keyboard
            keyboardType = KeyboardType.Email,
            imeAction = imeAction,
        )
    }
}
