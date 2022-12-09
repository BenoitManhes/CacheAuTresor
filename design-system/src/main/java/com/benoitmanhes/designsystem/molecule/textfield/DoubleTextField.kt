package com.benoitmanhes.designsystem.molecule.textfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.benoitmanhes.designsystem.atoms.CTDivider
import com.benoitmanhes.designsystem.atoms.CTSelector
import com.benoitmanhes.designsystem.atoms.CTTextView
import com.benoitmanhes.designsystem.atoms.spacer.SpacerTiny
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CTDoubleTextField(
    valueTop: String?,
    valueBottom: String?,
    onValueTopChanged: (String) -> Unit,
    onValueBottomChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelTop: TextSpec? = null,
    labelBottom: TextSpec? = null,
    errorText: TextSpec? = null,
    isError: Boolean = !errorText?.value()?.text.isNullOrEmpty(),
    color: Color = CTTheme.color.primary,
    errorColor: Color = CTTheme.color.error,
    textFieldTypeTop: TextFieldType = TextFieldType.STANDARD,
    textFieldTypeBottom: TextFieldType = TextFieldType.STANDARD,
    imeAction: ImeAction = ImeAction.Done,
    inputTypeTop: InputType = InputType.Default,
    inputTypeBottom: InputType = InputType.Default,
    optionsTop: Set<TextFieldOption> = emptySet(),
    optionsBottom: Set<TextFieldOption> = emptySet(),
    visualTransformationTop: VisualTransformation = VisualTransformation.None,
    visualTransformationBottom: VisualTransformation = VisualTransformation.None,
) {
    val shape = CTTheme.shape.medium
    val textFieldHeight = Dimens.Size.textFieldMinHeight
    var state by remember { mutableStateOf(State.None) }

    val borderColor: Color by animateColorAsState(
        targetValue = when {
            isError -> errorColor
            else -> CTTheme.color.placeholder
        }
    )
    val contentColor by animateColorAsState(targetValue = if (isError) errorColor else color)

    Column(modifier = modifier) {
        BoxWithConstraints {
            ConstraintLayout(constraintSet = constraints()) {
                Surface(
                    modifier = Modifier
                        .layoutId(BackgroundId),
                    shape = shape,
                    border = BorderStroke(CTTheme.stroke.thin, borderColor),
                ) { }
                if (state != State.None) {
                    val offset by animateDpAsState(
                        targetValue = if (state == State.Bottom) textFieldHeight else 0.dp
                    )
                    CTSelector(
                        modifier = Modifier
                            .focusable(false)
                            .offset(y = offset)
                            .height(textFieldHeight),
                        color = contentColor,
                        shape = shape,
                    )
                }
                Column(
                    modifier = Modifier
                        .layoutId(ContentId)
                        .wrapContentHeight(),
                ) {
                    CTBasicTextField(
                        value = valueTop,
                        onValueChange = onValueTopChanged,
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusEvent { focusState ->
                                if (focusState.hasFocus) {
                                    state = State.Top
                                } else if (state == State.Top) {
                                    state = State.None
                                }
                            },
                        labelText = labelTop,
                        inputType = inputTypeTop,
                        imeAction = ImeAction.Next,
                        colors = CTTextFieldColors(
                            textColor = CTTheme.color.onSurface,
                            color = contentColor,
                        ),
                        textFieldType = textFieldTypeTop,
                        options = optionsTop,
                        visualTransformation = visualTransformationTop,
                    )
                    if (state == State.None) {
                        CTDivider(
                            modifier = Modifier.fillMaxWidth(),
                            color = borderColor,
                        )
                    }
                    CTBasicTextField(
                        value = valueBottom,
                        onValueChange = onValueBottomChanged,
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusEvent { focusState ->
                                if (focusState.hasFocus) {
                                    state = State.Bottom
                                } else if (state == State.Bottom) {
                                    state = State.None
                                }
                            },
                        labelText = labelBottom,
                        inputType = inputTypeBottom,
                        imeAction = imeAction,
                        colors = CTTextFieldColors(
                            textColor = CTTheme.color.onSurface,
                            color = contentColor,
                        ),
                        textFieldType = textFieldTypeBottom,
                        options = optionsBottom,
                        visualTransformation = visualTransformationBottom,
                    )
                }
            }
        }
        SpacerTiny()
        AnimatedVisibility(visible = isError && errorText != null) {
            CTTextView(
                text = errorText!!,
                modifier = Modifier.padding(start = CTTheme.spacing.medium),
                color = errorColor,
                style = CTTheme.typography.caption,
            )
        }
    }
}

private fun constraints() = ConstraintSet {
    val background = createRefFor(BackgroundId)
    val content = createRefFor(ContentId)

    constrain(background) {
        top.linkTo(content.top)
        bottom.linkTo(content.bottom)
        start.linkTo(content.start)
        end.linkTo(content.end)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }
}

private enum class State { None, Top, Bottom }

private const val BackgroundId: String = "background.id"
private const val ContentId: String = "content.id"

@Preview
@Composable
private fun PreviewDoubleTextField() {
    var textTop by remember { mutableStateOf("") }
    var textBottom by remember { mutableStateOf("") }

    CTTheme {
        Box(
            modifier = Modifier
                .imePadding()
                .fillMaxSize()
                .padding(CTTheme.spacing.extraLarge),
            contentAlignment = Alignment.Center,
        ) {
            CTDoubleTextField(
                valueTop = textTop,
                valueBottom = textBottom,
                labelTop = TextSpec.RawString("Label top"),
                labelBottom = TextSpec.RawString("Label bottom"),
                errorText = TextSpec.RawString("Error message"),
                isError = textBottom.length > 8,
                onValueTopChanged = { textTop = it },
                onValueBottomChanged = { textBottom = it },
            )
        }
    }
}
