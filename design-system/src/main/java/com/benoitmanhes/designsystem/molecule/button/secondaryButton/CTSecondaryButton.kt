package com.benoitmanhes.designsystem.molecule.button.secondaryButton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.Mountain
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec

@Composable
fun CTSecondaryButton(
    text: TextSpec,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: SecondaryButtonType = SecondaryButtonType.Colored,
    enabled: Boolean = true,
    leadingIcon: IconSpec? = null,
    color: ComposeProvider<Color> = CTTheme.composed { this.color.surfacePrimary },
    contentColor: ComposeProvider<Color> = CTTheme.composed { this.color.textOnSurfacePrimary },
) {
    ButtonFromType(
        modifier = modifier,
        type = type,
        enabled = enabled,
        color = color(),
        contentColor = contentColor(),
        onClick = onClick,
    ) { buttonColors ->
        Row(
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leadingIcon?.let { leadingIcon ->
                CTIcon(
                    icon = leadingIcon,
                    size = Dimens.IconSize.Medium,
                )
            }
            // Text
            CTTextView(
                text = text,
                style = LocalTextStyle.current,
                color = buttonColors.contentColor(enabled = enabled).value,
                maxLine = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun ButtonFromType(
    modifier: Modifier,
    type: SecondaryButtonType,
    enabled: Boolean,
    color: Color,
    contentColor: Color,
    onClick: () -> Unit,
    content: @Composable (ButtonColors) -> Unit,
) {
    val safeOnClick = if (enabled) {
        onClick
    } else {
        {}
    }

    when (type) {
        SecondaryButtonType.Colored -> {
            Button(
                shape = CTTheme.shape.medium,
                colors = buttonColorsColored(color, contentColor),
                enabled = enabled,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = Dimens.Elevation.none,
                    pressedElevation = Dimens.Elevation.none,
                    hoveredElevation = Dimens.Elevation.none,
                    focusedElevation = Dimens.Elevation.none,
                ),
                modifier = modifier,
                onClick = safeOnClick,
                contentPadding = Dimens.SecondaryButton.padding,
            ) {
                ProvideTextStyle(value = CTTheme.typography.bodyBold) {
                    content(buttonColorsColored(color, contentColor))
                }
            }
        }

        SecondaryButtonType.Outlined -> {
            OutlinedButton(
                shape = CTTheme.shape.medium,
                colors = buttonColorsOutlined,
                enabled = enabled,
                modifier = modifier,
                onClick = safeOnClick,
                border = BorderStroke(
                    width = CTTheme.stroke.thin,
                    color = if (!enabled) {
                        CTTheme.color.strokeDisable
                    } else {
                        CTTheme.color.strokeBorder
                    },
                ),
                contentPadding = Dimens.SecondaryButton.padding,
            ) {
                ProvideTextStyle(value = CTTheme.typography.bodyBold) {
                    content(buttonColorsOutlined)
                }
            }
        }

        SecondaryButtonType.Text -> {
            TextButton(
                shape = CTTheme.shape.medium,
                colors = buttonTextColors(color),
                enabled = enabled,
                modifier = modifier,
                onClick = safeOnClick,
                contentPadding = Dimens.SecondaryButton.padding,
            ) {
                ProvideTextStyle(value = CTTheme.typography.bodyBold) {
                    content(buttonTextColors(color))
                }
            }
        }
    }
}

@Composable
private fun buttonColorsColored(color: Color, contentColor: Color) = ButtonDefaults.buttonColors(
    backgroundColor = color,
    contentColor = contentColor,
    disabledBackgroundColor = CTTheme.color.surfaceDisable,
    disabledContentColor = CTTheme.color.textDisable,
)

private val buttonColorsOutlined
    @Composable get() = ButtonDefaults.outlinedButtonColors(
        backgroundColor = Color.Transparent,
        contentColor = CTTheme.color.textOnSurface,
        disabledContentColor = CTTheme.color.textDisable,
    )

@Composable
private fun buttonTextColors(color: Color) = ButtonDefaults.textButtonColors(
    contentColor = color,
    disabledContentColor = CTTheme.color.textDisable,
)

@Preview
@Composable
private fun PreviewSecondaryButton() {
    CTTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium)
            ) {
                SecondaryButtonState(text = TextSpec.RawString("Button"), onClick = { }).Composable()
                SecondaryButtonState(
                    text = TextSpec.RawString("Button"),
                    type = SecondaryButtonType.Colored,
                    onClick = { },
                    leadingIcon = CTTheme.icon.Mountain,
                ).Composable()
                SecondaryButtonState(
                    text = TextSpec.RawString("Outlined Button"),
                    onClick = { },
                    type = SecondaryButtonType.Outlined,
                    leadingIcon = CTTheme.icon.Mountain,
                ).Composable()
                SecondaryButtonState(
                    text = TextSpec.RawString("Text Button"),
                    onClick = { },
                    type = SecondaryButtonType.Text,
                    leadingIcon = CTTheme.icon.Mountain,
                ).Composable()
                SecondaryButtonState(
                    text = TextSpec.RawString("Button"),
                    type = SecondaryButtonType.Outlined,
                    enabled = false,
                    onClick = { },
                )
            }
        }
    }
}
