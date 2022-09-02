package com.benoitmanhes.cacheautresor.common.composable.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.input.InputImeAction
import com.benoitmanhes.cacheautresor.common.composable.textview.TextView
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun SimpleTextField(
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
    value: String? = null,
    @StringRes labelRes: Int? = null,
    @StringRes placeHolderRes: Int? = null,
    textStyle: TextStyle = AppTheme.typography.body,
    shape: Shape = AppTheme.shape.mediumRoundedCornerShape,
    color: Color = MaterialTheme.colors.primary,
    backgroundColor: Color = MaterialTheme.colors.surface,
    inputImeAction: InputImeAction = InputImeAction.Default(LocalFocusManager.current) { },
    leftIcon: @Composable (() -> Unit)? = null,
    rightIcon: @Composable (() -> Unit)? = null,
    onFocusChange: (FocusState) -> Unit = { },
    onTextChanged: ((String) -> Unit) = { },
) {

    val hintLabel = if (value.isNullOrEmpty()) {
        placeHolderRes?.let { stringResource(it) }
    } else null

    Surface(
        modifier = modifier,
        shape = shape,
        elevation = Dimens.Elevation.none,
        color = backgroundColor,
    ) {
        CustomTextField(
            modifier = Modifier
                .height(Dimens.ComponentSize.textFieldHeight)
                .focusRequester(focusRequester)
                .onFocusChanged(onFocusChange),
            value = value.orEmpty(),
            onValueChange = onTextChanged,
            label = { TextView(textRes = labelRes, textAlign = TextAlign.Center) },
            placeholder = { TextView(text = hintLabel, style = textStyle) },
            singleLine = true,
            colors = SimpleTextFieldColors(color),
            leadingIcon = leftIcon,
            trailingIcon = rightIcon,
            keyboardOptions = KeyboardOptions(
                imeAction = inputImeAction.imeAction
            ),
            textStyle = textStyle,
            keyboardActions = inputImeAction.keyboardActions,
        )
    }
}

@Composable
private fun SimpleTextFieldColors(
    color: Color = MaterialTheme.colors.primary,
): TextFieldColors = TextFieldDefaults.textFieldColors(
    cursorColor = color,
    focusedLabelColor = color,
    focusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    backgroundColor = Color.Transparent,
)

@Preview
@Composable
private fun PreviewSimpleTextField() {
    AppTheme {
        val focusRequester = remember { FocusRequester() }
        var text by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray),
            contentAlignment = Alignment.Center,
        ) {
            Column {
                SimpleTextField(
                    value = text,
                    labelRes = R.string.bottomBar_explore,
                    onTextChanged = { text = it },
                    focusRequester = focusRequester,
                )
            }
        }
    }
}
