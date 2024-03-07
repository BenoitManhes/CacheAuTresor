package com.benoitmanhes.designsystem.molecule.button.primarybutton

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.theme.GradientColors
import com.benoitmanhes.designsystem.theme.composed

@Stable
data class PrimaryButtonState(
    val text: TextSpec,
    val onClick: () -> Unit,
    val type: PrimaryButtonType = PrimaryButtonType.COLORED,
    val status: ButtonStatus = ButtonStatus.ENABLE,
    val gradientBackground: ComposeProvider<GradientColors> = CTTheme.composed { color.gradientSurfacePrimary },
    val options: Set<PrimaryButtonOption> = emptySet(),
) {

    private val contentType = "PrimaryButton"

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
        contentColor: Color? = null,
    ) {
        CTPrimaryButton(
            text = text,
            onClick = onClick,
            modifier = modifier,
            type = type,
            status = status,
            gradient = gradientBackground(),
            contentColor = contentColor,
            options = options,
        )
    }

    fun item(
        scope: LazyListScope,
        key: Any = text.hashCode(),
        modifier: Modifier = Modifier,
        color: ComposeProvider<Color>? = null,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            Content(
                modifier = modifier,
                contentColor = color?.invoke(),
            )
        }
    }
}
