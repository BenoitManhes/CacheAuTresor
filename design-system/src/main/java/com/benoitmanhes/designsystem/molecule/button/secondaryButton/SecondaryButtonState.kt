package com.benoitmanhes.designsystem.molecule.button.secondaryButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec

@Stable
data class SecondaryButtonState(
    val text: TextSpec,
    val onClick: () -> Unit,
    val type: SecondaryButtonType = SecondaryButtonType.Colored,
    val enabled: Boolean = true,
    val leadingIcon: IconSpec? = null,
    val color: ComposeProvider<Color> = CTTheme.composed { this.color.primary },
    val contentColor: ComposeProvider<Color> = CTTheme.composed { this.color.onPrimary },
) {
    @Composable
    fun Composable(modifier: Modifier = Modifier) {
        CTSecondaryButton(this, modifier)
    }
}

enum class SecondaryButtonType {
    Colored, Outlined, Text
}
