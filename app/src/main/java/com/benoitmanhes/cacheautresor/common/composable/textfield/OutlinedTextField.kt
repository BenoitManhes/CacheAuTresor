package com.benoitmanhes.cacheautresor.common.composable.textfield

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.divider.Spacer
import com.benoitmanhes.cacheautresor.common.composable.textview.TextView
import com.benoitmanhes.cacheautresor.common.extension.getKeyboardOption
import com.benoitmanhes.cacheautresor.common.utils.InputType
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OutlinedTextField(
    modifier: Modifier = Modifier,
    value: String? = null,
    @StringRes labelRes: Int? = null,
    @StringRes placeHolderRes: Int? = null,
    errorText: String? = null,
    isError: Boolean = !errorText.isNullOrEmpty(),
    textStyle: TextStyle = AppTheme.typography.body,
    textColor: Color = MaterialTheme.colors.onSurface,
    color: Color = MaterialTheme.colors.primary,
    errorColor: Color = MaterialTheme.colors.error,
    backgroundColor: Color = MaterialTheme.colors.surface,
    hasNext: Boolean = false,
    inputType: InputType = InputType.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leftIcon: @Composable (() -> Unit)? = null,
    rightIcon: @Composable (() -> Unit)? = null,
    onFocusChange: (FocusState) -> Unit = { },
    onTextChanged: ((String) -> Unit) = { },
) {
    var hasFocus: Boolean by remember { mutableStateOf(false) }

    val hintLabel = if (value.isNullOrEmpty()) {
        placeHolderRes?.let { stringResource(it) }
    } else null

    val borderStroke: Dp by animateDpAsState(targetValue = if (hasFocus) Dimens.Stroke.strong else Dimens.Stroke.thin)
    val borderColor: Color by animateColorAsState(
        targetValue = when {
            isError -> errorColor
            hasFocus -> color
            else -> AppTheme.colors.placeholder
        }
    )

    Column {
        Surface(
            modifier = modifier,
            shape = AppTheme.shape.mediumRoundedCornerShape,
            elevation = Dimens.Elevation.none,
            color = backgroundColor,
        ) {
            CustomTextField(
                modifier = Modifier
                    .height(Dimens.ComponentSize.textFieldHeight)
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        hasFocus = focusState.hasFocus
                        onFocusChange(focusState)
                    }
                    .border(borderStroke, borderColor, AppTheme.shape.mediumRoundedCornerShape),
                value = value.orEmpty(),
                onValueChange = onTextChanged,
                label = { TextView(textRes = labelRes, textAlign = TextAlign.Center) },
                placeholder = { TextView(text = hintLabel, style = textStyle) },
                singleLine = true,
                isError = isError,
                colors = OutlinedTextFieldColors(textColor = textColor, color = color),
                leadingIcon = leftIcon,
                trailingIcon = rightIcon,
                keyboardOptions = inputType.getKeyboardOption(hasNext),
                textStyle = textStyle,
                visualTransformation = visualTransformation,
            )
        }
        Spacer(size = Dimens.Margin.small)
        AnimatedVisibility(visible = isError && errorText != null) {
            TextView(
                modifier = Modifier.padding(start = Dimens.Margin.medium + Dimens.Margin.small),
                text = errorText,
                color = errorColor,
                style = AppTheme.typography.caption,
            )
        }
    }
}

@Composable
private fun OutlinedTextFieldColors(
    textColor: Color,
    color: Color,
): TextFieldColors = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    cursorColor = color,
    focusedLabelColor = color,
    focusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    backgroundColor = Color.Transparent,
)

@Preview
@Composable
private fun PreviewOutlinedTextField() {
    AppTheme {
        var text by remember { mutableStateOf("") }
        var text2 by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray),
            contentAlignment = Alignment.Center,
        ) {
            Column {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    labelRes = R.string.bottomBar_explore,
                    onTextChanged = { text = it },
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text2,
                    labelRes = R.string.logenScreen_register_passwordTextField_label,
                    isError = true,
                    errorText = "Error message",
                    onTextChanged = { text2 = it },
                )
            }
        }
    }
}
