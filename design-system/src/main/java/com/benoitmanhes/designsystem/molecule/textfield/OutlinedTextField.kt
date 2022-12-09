package com.benoitmanhes.designsystem.molecule.textfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import com.benoitmanhes.designsystem.atoms.CTTextView
import com.benoitmanhes.designsystem.atoms.spacer.SpacerTiny
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CTOutlinedTextField(
    value: String?,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelText: TextSpec? = null,
    enabled: Boolean = true,
    options: Set<TextFieldOption> = emptySet(),
    isError: Boolean = false,
    errorText: TextSpec? = null,
    color: Color = CTTheme.color.primary,
    imeAction: ImeAction = ImeAction.Done,
    inputType: InputType = InputType.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    kbController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) {
    var hasFocus: Boolean by remember { mutableStateOf(false) }

    val borderStroke: Dp by animateDpAsState(targetValue = if (hasFocus) CTTheme.stroke.strong else CTTheme.stroke.thin)
    val borderColor: Color by animateColorAsState(
        targetValue = when {
            isError -> CTTheme.color.error
            hasFocus -> color
            else -> CTTheme.color.placeholder
        }
    )
    val errorVisible = isError && errorText != null

    Column {
        Surface(
            modifier = modifier,
            shape = CTTheme.shape.medium,
            elevation = CTTheme.elevation.none,
            color = CTTheme.color.surface,
        ) {
            CTBasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        hasFocus = focusState.hasFocus
                    }
                    .border(borderStroke, borderColor, CTTheme.shape.medium),
                labelText = labelText,
                enabled = enabled,
                textStyle = CTTheme.typography.body,
                options = options,
                isError = isError,
                visualTransformation = visualTransformation,
                kbController = kbController,
                inputType = inputType,
                imeAction = imeAction,
                singleLine = true,
                colors = CTTextFieldColors(
                    textColor = CTTheme.color.onSurface,
                    color = color,
                ),
            )
        }
        if (errorVisible) {
            SpacerTiny()
        }
        AnimatedVisibility(visible = errorVisible) {
            CTTextView(
                modifier = Modifier.padding(start = CTTheme.spacing.medium),
                text = errorText!!,
                color = CTTheme.color.error,
                style = CTTheme.typography.caption,
            )
        }
    }
}
