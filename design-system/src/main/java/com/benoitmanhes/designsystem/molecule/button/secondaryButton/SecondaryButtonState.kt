package com.benoitmanhes.designsystem.molecule.button.secondaryButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec

@Stable
data class SecondaryButtonState(
    val text: TextSpec,
    val onClick: () -> Unit,
    val type: SecondaryButtonType = SecondaryButtonType.Colored,
    val enabled: Boolean = true,
    val leadingIcon: IconSpec? = null,
    val color: ComposeProvider<Color> = CTTheme.composed { this.color.surfacePrimary },
    val contentColor: ComposeProvider<Color> = CTTheme.composed { this.color.textOnSurfacePrimary },
) {
    @Composable
    fun Composable(modifier: Modifier = Modifier) {
        CTSecondaryButton(
            text = text,
            onClick = onClick,
            type = type,
            enabled = enabled,
            leadingIcon = leadingIcon,
            color = color,
            contentColor = contentColor,
            modifier = modifier,
        )
    }
}

enum class SecondaryButtonType {
    Colored, Outlined, Text
}
