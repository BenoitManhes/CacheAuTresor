package com.benoitmanhes.cacheautresor.common.composable.button

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun StyleButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary,
    textColor: Color = AppTheme.colors.onPrimary,
    buttonStyle: ButtonStyle = ButtonStyle.Filled,
    isEnabled: (ButtonStyle) -> Boolean = { true },
    isLoading: Boolean = false,
    onClick: () -> Unit = { },
) {
    Crossfade(targetState = buttonStyle) { style ->
        when (style) {
            ButtonStyle.Filled -> {
                LoadingFilledButton(
                    text = text,
                    modifier = modifier.heightIn(Dimens.ComponentSize.buttonHeight),
                    enabled = isEnabled(style),
                    color = color,
                    textColor = textColor,
                    onClick = onClick,
                    isLoading = isLoading,
                )
            }
            ButtonStyle.Outlined -> {
                val outlinedColor = if (isEnabled(style)) AppTheme.colors.onSurface else AppTheme.colors.disable
                OutlineButton(
                    text = text,
                    modifier = modifier.heightIn(min = Dimens.ComponentSize.buttonHeight),
                    enabled = isEnabled(style),
                    color = outlinedColor,
                    onClick = onClick,
                )
            }
        }
    }
}

enum class ButtonStyle {
    Filled,
    Outlined,
}
