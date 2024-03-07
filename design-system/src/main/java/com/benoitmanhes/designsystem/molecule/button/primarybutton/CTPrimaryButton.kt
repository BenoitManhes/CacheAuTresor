package com.benoitmanhes.designsystem.molecule.button.primarybutton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.animation.LoadingDotAnimation
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.GradientColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CTPrimaryButton(
    text: TextSpec,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: PrimaryButtonType = PrimaryButtonType.COLORED,
    status: ButtonStatus = ButtonStatus.ENABLE,
    gradient: GradientColors = CTTheme.color.gradientSurfacePrimary,
    contentColor: Color? = null,
    options: Set<PrimaryButtonOption> = emptySet(),
) {
    ButtonFromType(
        status = status,
        modifier = modifier.heightIn(Dimens.Size.primaryButtonMinHeight),
        type = type,
        gradient = gradient,
        contentColor = contentColor,
        onClick = onClick,
    ) { buttonColors ->
        if (status == ButtonStatus.LOADING) {
            // Lottie animation
            LoadingDotAnimation(
                color = buttonColors.contentColor(enabled = true).value,
                modifier = Modifier
                    .size(Dimens.Size.lottieAnimationButton)
            )
        } else {
            Row(
                modifier = Modifier.padding(horizontal = CTTheme.spacing.medium),
                horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                options.filterIsInstance<PrimaryButtonOption.LeadingIcon>().firstOrNull()?.let { leadingIcon ->
                    CTIcon(
                        icon = leadingIcon.icon(),
                        size = Dimens.IconSize.Medium,
                    )
                }
                // Text
                CTTextView(
                    text = text,
                    style = LocalTextStyle.current,
                    color = buttonColors.contentColor(enabled = status != ButtonStatus.DISABLE).value,
                    maxLine = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun ButtonFromType(
    modifier: Modifier,
    type: PrimaryButtonType,
    status: ButtonStatus,
    gradient: GradientColors,
    contentColor: Color?,
    onClick: () -> Unit,
    content: @Composable (ButtonColors) -> Unit,
) {
    val safeOnClick = if (status == ButtonStatus.ENABLE) {
        onClick
    } else {
        {}
    }

    when (type) {
        PrimaryButtonType.COLORED -> {
            if (status == ButtonStatus.DISABLE) {
                Button(
                    shape = CTTheme.shape.medium,
                    colors = buttonColorsColored(CTTheme.color.surfaceDisable, CTTheme.color.textOnSurfaceDisable),
                    enabled = false,
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = Dimens.Elevation.none,
                        pressedElevation = Dimens.Elevation.none,
                        hoveredElevation = Dimens.Elevation.none,
                        focusedElevation = Dimens.Elevation.none,
                    ),
                    modifier = modifier,
                    onClick = safeOnClick,
                ) {
                    ProvideTextStyle(value = CTTheme.typography.bodyBold) {
                        content(buttonColorsColored(CTTheme.color.surfaceDisable, CTTheme.color.textOnSurfaceDisable))
                    }
                }
            } else {
                GradientButton(
                    shape = CTTheme.shape.medium,
                    gradient = Brush.linearGradient(gradient),
                    contentColor = CTTheme.color.textOnSurfacePrimary,
                    modifier = modifier,
                    onClick = safeOnClick,
                ) {
                    ProvideTextStyle(value = CTTheme.typography.bodyBold) {
                        content(
                            buttonColorsColored(gradient.first(), contentColor ?: CTTheme.color.textOnSurfacePrimary)
                        )
                    }
                }
            }
        }

        PrimaryButtonType.OUTLINED -> {
            OutlinedButton(
                shape = CTTheme.shape.medium,
                colors = buttonColorsOutlined,
                enabled = status != ButtonStatus.DISABLE,
                modifier = modifier,
                onClick = safeOnClick,
                border = BorderStroke(
                    width = CTTheme.stroke.medium,
                    color = if (status == ButtonStatus.DISABLE) {
                        CTTheme.color.strokeDisable
                    } else {
                        CTTheme.color.strokeBorder
                    },
                )
            ) {
                ProvideTextStyle(value = CTTheme.typography.bodyBold) {
                    content(buttonColorsOutlined)
                }
            }
        }

        PrimaryButtonType.TEXT -> {
            TextButton(
                shape = CTTheme.shape.medium,
                colors = buttonTextColors(contentColor ?: CTTheme.color.textPrimary),
                enabled = status != ButtonStatus.DISABLE,
                modifier = modifier,
                onClick = safeOnClick,
            ) {
                ProvideTextStyle(value = CTTheme.typography.bodyBold) {
                    content(buttonTextColors(contentColor ?: CTTheme.color.textPrimary))
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
    disabledContentColor = CTTheme.color.textOnSurfaceDisable,
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
private fun PreviewCTButton() {
    CTTheme {
        var status by remember { mutableStateOf(ButtonStatus.ENABLE) }
        val scope = rememberCoroutineScope()

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium)
            ) {
                CTPrimaryButton(text = TextSpec.RawString("Button"), onClick = { })
                CTPrimaryButton(
                    text = TextSpec.RawString("Button"),
                    type = PrimaryButtonType.COLORED,
                    status = ButtonStatus.ENABLE,
                    onClick = { },
                    options = setOf(PrimaryButtonOption.LeadingIcon { CTTheme.icon.Mountain }),
                )
                CTPrimaryButton(
                    text = TextSpec.RawString("Outlined Button"),
                    onClick = { },
                    type = PrimaryButtonType.OUTLINED,
                    options = setOf(PrimaryButtonOption.LeadingIcon { CTTheme.icon.Mountain }),
                )
                CTPrimaryButton(
                    text = TextSpec.RawString("Text Button"),
                    onClick = { },
                    type = PrimaryButtonType.TEXT,
                    options = setOf(PrimaryButtonOption.LeadingIcon { CTTheme.icon.Mountain }),
                )
                CTPrimaryButton(
                    text = TextSpec.RawString("Button"),
                    type = PrimaryButtonType.COLORED,
                    status = ButtonStatus.LOADING,
                    onClick = { },
                )
                CTPrimaryButton(text = TextSpec.RawString("Button"), type = PrimaryButtonType.OUTLINED, onClick = { })
                CTPrimaryButton(
                    text = TextSpec.RawString("Button"),
                    type = PrimaryButtonType.OUTLINED,
                    status = ButtonStatus.LOADING,
                    onClick = { },
                )
                CTPrimaryButton(
                    text = TextSpec.RawString("Button"),
                    type = PrimaryButtonType.OUTLINED,
                    status = ButtonStatus.DISABLE,
                    onClick = { },
                )
                CTPrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(CTTheme.spacing.huge),
                    text = TextSpec.RawString("Primary button"),
                    status = status,
                    onClick = {
                        scope.launch {
                            status = ButtonStatus.LOADING
                            delay(2000)
                            status = ButtonStatus.ENABLE
                        }
                    }
                )
            }
        }
    }
}
