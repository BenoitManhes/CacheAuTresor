package com.benoitmanhes.cacheautresor.common.composable.textfield

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.divider.HorizontalDivider
import com.benoitmanhes.cacheautresor.common.composable.input.InputImeAction
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun DoubleTextField(
    valueTop: String?,
    valueBottom: String?,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
    @StringRes labelTopRes: Int? = null,
    @StringRes labelBottomRes: Int? = null,
    color: Color = AppTheme.colors.primary,
    onTextTopChanged: ((String) -> Unit) = { },
    onTextBottomChanged: ((String) -> Unit) = { },
) {
    val shape = AppTheme.shape.mediumRoundedCornerShape
    var state by remember { mutableStateOf(State.None) }

    BoxWithConstraints(modifier = modifier) {
        ConstraintLayout(constraintSet = constraints()) {
            Surface(
                modifier = Modifier
                    .layoutId(BACKGROUND_ID),
                shape = shape,
                border = BorderStroke(Dimens.Stroke.thin, AppTheme.colors.placeholder),
            ) { }
            if (state != State.None) {
                val offset by animateDpAsState(
                    targetValue = if (state == State.Bottom) Dimens.ComponentSize.textFieldHeight else 0.dp
                )
                Selector(
                    modifier = Modifier
                        .focusable(false)
                        .offset(y = offset),
                    color = color,
                    shape = shape,
                )
            }
            Column(
                modifier = Modifier
                    .layoutId(CONTENT_ID)
                    .wrapContentHeight(),
            ) {
                SimpleTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusEvent { focusState ->
                            if (focusState.hasFocus) {
                                state = State.Top
                            } else if (state == State.Top) {
                                state = State.None
                            }
                        },
                    value = valueTop,
                    labelRes = labelTopRes,
                    focusRequester = focusRequester,
                    color = color,
                    backgroundColor = Color.Transparent,
                    onTextChanged = onTextTopChanged,
                    inputImeAction = InputImeAction.Next(LocalFocusManager.current, focusRequester),
                )
                if (state == State.None) {
                    HorizontalDivider()
                }
                SimpleTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusEvent { focusState ->
                            if (focusState.hasFocus) {
                                state = State.Bottom
                            } else if (state == State.Bottom) {
                                state = State.None
                            }
                        },
                    value = valueBottom,
                    labelRes = labelBottomRes,
                    focusRequester = focusRequester,
                    color = color,
                    backgroundColor = Color.Transparent,
                    onTextChanged = onTextBottomChanged,
                    inputImeAction = InputImeAction.Done(LocalFocusManager.current) { },
                )
            }
        }
    }
}

private fun constraints() = ConstraintSet {
    val background = createRefFor(BACKGROUND_ID)
    val content = createRefFor(CONTENT_ID)

    constrain(background) {
        top.linkTo(content.top)
        bottom.linkTo(content.bottom)
        start.linkTo(content.start)
        end.linkTo(content.end)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }
}

@Composable
private fun Selector(modifier: Modifier = Modifier, color: Color, shape: Shape) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.ComponentSize.textFieldHeight),
        color = Color.Transparent,
        border = BorderStroke(Dimens.Stroke.strong, color),
        shape = shape,
    ) { }
}

private enum class State { None, Top, Bottom }
private const val BACKGROUND_ID: String = "background.id"
private const val CONTENT_ID: String = "content.id"

@Preview
@Composable
private fun PreviewDoubleTextField() {
    AppTheme {
        val focusRequester = remember { FocusRequester() }
        var textTop by remember { mutableStateOf("") }
        var textBottom by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp), contentAlignment = Alignment.TopCenter
        ) {
            DoubleTextField(
                valueTop = textTop,
                valueBottom = textBottom,
                focusRequester = focusRequester,
                labelTopRes = R.string.loginScreen_login_emailTextField_label,
                labelBottomRes = R.string.loginScreen_login_passwordTextField_label,
                onTextTopChanged = { textTop = it },
                onTextBottomChanged = { textBottom = it },
                color = AppTheme.colors.secondary,
            )
        }
    }
}
