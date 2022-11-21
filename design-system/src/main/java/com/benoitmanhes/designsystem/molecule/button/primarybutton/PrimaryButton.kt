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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
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
import com.benoitmanhes.designsystem.atoms.LoadingDotAnimation
import com.benoitmanhes.designsystem.atoms.TextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.Dimens
import com.benoitmanhes.designsystem.theme.colorScheme
import com.benoitmanhes.designsystem.theme.corner
import com.benoitmanhes.designsystem.theme.spacing
import com.benoitmanhes.designsystem.theme.stroke
import com.benoitmanhes.designsystem.theme.typo
import com.benoitmanhes.designsystem.utils.TextSpec
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PrimaryButton(
    text: TextSpec,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: PrimaryButtonType = PrimaryButtonType.COLORED,
    status: PrimaryButtonStatus = PrimaryButtonStatus.ENABLE,
    options: Set<PrimaryButtonOption> = emptySet(),
) {
    ButtonFromType(
        status = status,
        modifier = modifier.heightIn(Dimens.Size.primaryButtonMinHeight),
        type = type,
        onClick = onClick,
    ) { buttonColors ->
        if (status == PrimaryButtonStatus.LOADING) {
            // Lottie animation
            LoadingDotAnimation(
                color = buttonColors.contentColor(enabled = true).value,
                modifier = Modifier
                    .size(Dimens.Size.lottieAnimationButton)
            )
        } else {
            // Text
            TextView(
                text = text,
                style = LocalTextStyle.current,
                color = buttonColors.contentColor(enabled = status != PrimaryButtonStatus.DISABLE).value,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                maxLine = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun ButtonFromType(
    type: PrimaryButtonType,
    status: PrimaryButtonStatus,
    modifier: Modifier,
    onClick: () -> Unit,
    content: @Composable (ButtonColors) -> Unit,
) {
    val safeOnClick = if (status == PrimaryButtonStatus.ENABLE) {
        onClick
    } else {
        {}
    }

    when (type) {
        PrimaryButtonType.COLORED -> {
            Button(
                shape = RoundedCornerShape(MaterialTheme.corner.medium),
                colors = buttonColorsColored,
                enabled = status != PrimaryButtonStatus.DISABLE,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = Dimens.Elevation.none,
                    pressedElevation = Dimens.Elevation.none,
                    hoveredElevation = Dimens.Elevation.none,
                    focusedElevation = Dimens.Elevation.none,
                ),
                modifier = modifier,
                onClick = safeOnClick,
            ) {
                ProvideTextStyle(value = MaterialTheme.typo.bodyBold) {
                    content(buttonColorsColored)
                }
            }
        }
        PrimaryButtonType.OUTLINED -> {
            OutlinedButton(
                shape = RoundedCornerShape(MaterialTheme.corner.medium),
                colors = buttonColorsOutlined,
                enabled = status != PrimaryButtonStatus.DISABLE,
                modifier = modifier,
                onClick = safeOnClick,
                border = BorderStroke(
                    width = MaterialTheme.stroke.thin,
                    color = if (status == PrimaryButtonStatus.DISABLE) {
                        MaterialTheme.colorScheme.disable
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                )
            ) {
                ProvideTextStyle(value = MaterialTheme.typo.bodyBold) {
                    content(buttonColorsOutlined)
                }
            }
        }
    }
}

private val buttonColorsColored
    @Composable get() = ButtonDefaults.buttonColors(
        disabledBackgroundColor = MaterialTheme.colorScheme.disable,
        disabledContentColor = MaterialTheme.colorScheme.onDisable,
    )

private val buttonColorsOutlined
    @Composable get() = ButtonDefaults.outlinedButtonColors(
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContentColor = MaterialTheme.colorScheme.disable,
    )

@Preview
@Composable
private fun PreviewCTButton() {
    CTTheme {
        var status by remember { mutableStateOf(PrimaryButtonStatus.ENABLE) }
        val scope = rememberCoroutineScope()

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
            ) {
                PrimaryButton(text = TextSpec.RawString("Button"), onClick = { })
                PrimaryButton(
                    text = TextSpec.RawString("Button"),
                    type = PrimaryButtonType.COLORED,
                    status = PrimaryButtonStatus.ENABLE,
                    onClick = { },
                )
                PrimaryButton(
                    text = TextSpec.RawString("Button"),
                    type = PrimaryButtonType.COLORED,
                    status = PrimaryButtonStatus.LOADING,
                    onClick = { },
                )
                PrimaryButton(text = TextSpec.RawString("Button"), type = PrimaryButtonType.OUTLINED, onClick = { })
                PrimaryButton(
                    text = TextSpec.RawString("Button"),
                    type = PrimaryButtonType.OUTLINED,
                    status = PrimaryButtonStatus.LOADING,
                    onClick = { },
                )
                PrimaryButton(
                    text = TextSpec.RawString("Button"),
                    type = PrimaryButtonType.OUTLINED,
                    status = PrimaryButtonStatus.DISABLE,
                    onClick = { },
                )
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.huge),
                    text = TextSpec.RawString("Primary button"),
                    status = status,
                    onClick = {
                        scope.launch {
                            status = PrimaryButtonStatus.LOADING
                            delay(2000)
                            status = PrimaryButtonStatus.ENABLE
                        }
                    }
                )
            }
        }
    }
}
