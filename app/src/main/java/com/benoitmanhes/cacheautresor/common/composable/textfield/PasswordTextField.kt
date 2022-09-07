package com.benoitmanhes.cacheautresor.common.composable.textfield

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.input.InputImeAction
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.res.icons.AppIconPack
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconSmallEye
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconSmallEyeClose
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun PasswordTextField(
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
    value: String? = null,
    @StringRes labelRes: Int? = null,
    @StringRes placeHolderRes: Int? = null,
    textStyle: TextStyle = AppTheme.typography.body,
    shape: Shape = AppTheme.shape.mediumRoundedCornerShape,
    textColor: Color = MaterialTheme.colors.onSurface,
    color: Color = MaterialTheme.colors.primary,
    backgroundColor: Color = MaterialTheme.colors.surface,
    hideText: Boolean = true,
    isEyeIconVisible: Boolean = false,
    inputImeAction: InputImeAction = InputImeAction.Default(LocalFocusManager.current) { },
    onFocusChange: (FocusState) -> Unit = { },
    onTextChanged: (String) -> Unit = { },
    onClickEyeIcon: () -> Unit = { },
) {
    SimpleTextField(
        focusRequester = focusRequester,
        modifier = modifier,
        value = value,
        labelRes = labelRes,
        placeHolderRes = placeHolderRes,
        textStyle = textStyle,
        shape = shape,
        textColor = textColor,
        color = color,
        backgroundColor = backgroundColor,
        inputImeAction = inputImeAction,
        onFocusChange = onFocusChange,
        onTextChanged = onTextChanged,
        visualTransformation = if (hideText) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardType = KeyboardType.Password,
        rightIcon = {
            val image = if (hideText) AppIconPack.IconSmallEye else AppIconPack.IconSmallEyeClose

            AnimatedVisibility(
                visible = isEyeIconVisible,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                IconButton(onClick = onClickEyeIcon) {
                    Icon(
                        imageVector = image,
                        contentDescription = null,
                        modifier = Modifier.size(Dimens.Size.passwordIconSize),
                        tint = textColor,
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewPasswordTextField() {
    AppTheme {
        val focusRequester = remember { FocusRequester() }
        var text by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.Margin.great),
            contentAlignment = Alignment.Center,
        ) {
            PasswordTextField(
                value = text,
                labelRes = R.string.bottomBar_explore,
                onTextChanged = { text = it },
                focusRequester = focusRequester,
            )
        }
    }
}