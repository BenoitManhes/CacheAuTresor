package com.benoitmanhes.designsystem.molecule.button.primarybutton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.atoms.animation.LoadingDotAnimation
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CTPrimaryButton(
    state: PrimaryButtonState,
    modifier: Modifier = Modifier,
    color: Color = CTTheme.color.primary,
) {
    CTPrimaryButton(
        text = state.text,
        onClick = state.onClick,
        modifier = modifier,
        type = state.type,
        status = state.status,
        color = color,
        options = state.options,
    )
}

@Composable
fun CTPrimaryButton(
    text: TextSpec,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: PrimaryButtonType = PrimaryButtonType.COLORED,
    status: ButtonStatus = ButtonStatus.ENABLE,
    color: Color = CTTheme.color.primary,
    contentColor: Color = CTTheme.color.onPrimary,
    options: Set<PrimaryButtonOption> = emptySet(),
) {
    ButtonFromType(
        status = status,
        modifier = modifier.heightIn(Dimens.Size.primaryButtonMinHeight),
        type = type,
        color = color,
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
            // Text
            CTTextView(
                text = text,
                style = LocalTextStyle.current,
                color = buttonColors.contentColor(enabled = status != ButtonStatus.DISABLE).value,
                modifier = Modifier.padding(horizontal = CTTheme.spacing.medium),
                maxLine = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun ButtonFromType(
    modifier: Modifier,
    type: PrimaryButtonType,
    status: ButtonStatus,
    color: Color,
    contentColor: Color,
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
            Button(
                shape = CTTheme.shape.medium,
                colors = buttonColorsColored(color, contentColor),
                enabled = status != ButtonStatus.DISABLE,
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
                    content(buttonColorsColored(color, contentColor))
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
                    width = CTTheme.stroke.thin,
                    color = if (status == ButtonStatus.DISABLE) {
                        CTTheme.color.disable
                    } else {
                        CTTheme.color.onSurface
                    },
                )
            ) {
                ProvideTextStyle(value = CTTheme.typography.bodyBold) {
                    content(buttonColorsOutlined)
                }
            }
        }
    }
}

@Composable
private fun buttonColorsColored(color: Color, contentColor: Color) = ButtonDefaults.buttonColors(
    backgroundColor = color,
    contentColor = contentColor,
    disabledBackgroundColor = CTTheme.color.disable,
    disabledContentColor = CTTheme.color.onDisable,
)

private val buttonColorsOutlined
    @Composable get() = ButtonDefaults.outlinedButtonColors(
        backgroundColor = Color.Transparent,
        contentColor = CTTheme.color.onSurface,
        disabledContentColor = CTTheme.color.disable,
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
