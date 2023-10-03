package com.benoitmanhes.designsystem.molecule.textfield

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.designsystem.molecule.button.iconbutton.CTIconButton
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ZoneTextField(
    state: ZoneTextFieldState,
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current,
) {
    var hasFocus: Boolean by remember { mutableStateOf(false) }
    var currentValue by rememberSaveable {
        mutableStateOf(state.initialValue)
    }

    Column(
        modifier = modifier
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
        horizontalAlignment = Alignment.End,
    ) {
        Surface(
            shape = CTTheme.shape.medium,
            elevation = CTTheme.elevation.none,
            color = CTTheme.color.surface,
            border = BorderStroke(CTTheme.stroke.thin, CTTheme.color.placeholder),
        ) {
            CTBasicTextField(
                value = currentValue,
                onValueChange = { currentValue = it },
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(min = Dimens.Text.zoneTextFieldMinHeight)
                    .onFocusChanged { focusState ->
                        hasFocus = focusState.hasFocus
                    },
                colors = CTTextFieldColors(
                    textColor = CTTheme.color.onSurface,
                    color = CTTheme.color.onSurface,
                ),
                imeAction = ImeAction.Default,
            )
        }
        if (hasFocus) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.extraSmall),
            ) {
                CTIconButton(
                    icon = Icons.Rounded.Clear.toIconSpec(),
                    size = Dimens.IconButtonSize.Medium,
                ) {
                    currentValue = state.initialValue
                    focusManager.clearFocus()
                }
                CTIconButton(
                    icon = Icons.Rounded.Check.toIconSpec(),
                    size = Dimens.IconButtonSize.Medium,
                ) {
                    state.onValueSaved(currentValue)
                    focusManager.clearFocus()
                }
            }
        }
    }
}

@Stable
data class ZoneTextFieldState(
    val initialValue: String?,
    val onValueSaved: (String?) -> Unit,
)

@Preview
@Composable
private fun PreviewZoneTextField() {
    CTTheme {
        var text: String? by remember {
            mutableStateOf(null)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(CTTheme.spacing.large),
            contentAlignment = Alignment.Center,
        ) {
            ZoneTextField(
                ZoneTextFieldState(
                    initialValue = text,
                    onValueSaved = { text = it },
                )
            )
        }
    }
}
