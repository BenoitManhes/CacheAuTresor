package com.benoitmanhes.cacheautresor.common.composable.textfield

import android.graphics.drawable.shapes.Shape
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.divider.Spacer
import com.benoitmanhes.cacheautresor.common.composable.input.InputImeAction
import com.benoitmanhes.cacheautresor.common.composable.textview.TextView
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun OutlinedTextField(
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
    value: String? = null,
    isError: Boolean = false,
    @StringRes labelRes: Int? = null,
    @StringRes placeHolderRes: Int? = null,
    @StringRes errorRes: Int? = null,
    textStyle: TextStyle = AppTheme.typography.body,
    textColor: Color = MaterialTheme.colors.onSurface,
    color: Color = MaterialTheme.colors.primary,
    errorColor: Color = MaterialTheme.colors.error,
    backgroundColor: Color = MaterialTheme.colors.surface,
    inputImeAction: InputImeAction = InputImeAction.Default(LocalFocusManager.current) { },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
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
                    .focusRequester(focusRequester)
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
                keyboardOptions = KeyboardOptions(
                    imeAction = inputImeAction.imeAction,
                    keyboardType = keyboardType,
                ),
                textStyle = textStyle,
                keyboardActions = inputImeAction.keyboardActions,
                visualTransformation = visualTransformation,
            )
        }
        Spacer(size = Dimens.Margin.small)
        AnimatedVisibility(visible = isError && errorRes != null) {
            TextView(
                modifier = Modifier.padding(start = Dimens.Margin.medium + Dimens.Margin.small),
                text = stringResource(id = errorRes!!),
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
        val focusRequester = remember { FocusRequester() }
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
                    focusRequester = focusRequester,
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text2,
                    labelRes = R.string.logenScreen_register_passwordTextField_label,
                    isError = true,
                    errorRes = R.string.bottomBar_home,
                    onTextChanged = { text2 = it },
                    focusRequester = focusRequester,
                )
            }
        }
    }
}
