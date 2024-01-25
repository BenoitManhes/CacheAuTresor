package com.benoitmanhes.designsystem.molecule.textfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.CTIconPack
import com.benoitmanhes.designsystem.res.icons.iconpack.EyeClose
import com.benoitmanhes.designsystem.res.icons.iconpack.EyeOpen
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ComposableContent
import com.benoitmanhes.designsystem.utils.IconSpec

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun CTBasicTextField(
    value: String?,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelText: TextSpec? = null,
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    isError: Boolean = false,
    textFieldType: TextFieldType = TextFieldType.STANDARD,
    options: Set<TextFieldOption> = emptySet(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    kbController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    focusManager: FocusManager = LocalFocusManager.current,
    imeAction: ImeAction = ImeAction.Done,
    inputType: InputType = InputType.Default,
    keyboardActions: KeyboardActions = KeyboardActions(
        onDone = {
            kbController?.hide()
            focusManager.clearFocus()
        },
    ),
    singleLine: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape =
        CTTheme.shape.small.copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
    contentPadding: PaddingValues =
        if (labelText?.value() == null) CTTheme.padding.textFieldDefault else CTTheme.padding.textFieldLabel,
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    val leadingIcon: ComposableContent? = options.filterIsInstance<TextFieldOption.LeadingIcon>().firstOrNull()?.let {
        {
            CTIcon(icon = it.icon, size = Dimens.IconSize.Medium)
        }
    }

    val actionIcon: ComposableContent? = options.filterIsInstance<TextFieldOption.ActionIcon>().firstOrNull()?.let {
        {
            CTIcon(icon = it.icon, onClick = it.onClick, size = Dimens.IconSize.Medium)
        }
    }

    when (textFieldType) {
        TextFieldType.STANDARD -> {
            BasicTextField(
                value = value.orEmpty(),
                modifier = modifier
                    .background(colors.backgroundColor(enabled).value, shape)
                    .indicatorLine(enabled, isError, interactionSource, colors)
                    // Override min size()
                    .defaultMinSize(
                        minWidth = Dimens.Size.textFieldMinWidth,
                        minHeight = Dimens.Size.textFieldMinHeight,
                    ),
                onValueChange = onValueChange,
                enabled = enabled,
                textStyle = mergedTextStyle,
                cursorBrush = SolidColor(colors.cursorColor(isError).value),
                visualTransformation = visualTransformation,
                keyboardOptions = inputType.getKeyboardOption(imeAction),
                keyboardActions = keyboardActions,
                interactionSource = interactionSource,
                singleLine = singleLine,
                minLines = minLines,
                maxLines = maxLines,
                decorationBox = @Composable { innerTextField ->
                    // places leading icon, text field with label and placeholder, trailing icon
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = value.orEmpty(),
                        visualTransformation = visualTransformation,
                        innerTextField = innerTextField,
                        label = {
                            labelText?.let { safeLabel ->
                                CTTextView(text = safeLabel)
                            }
                        },
                        leadingIcon = leadingIcon,
                        trailingIcon = actionIcon,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        contentPadding = contentPadding,
                    )
                }
            )
        }

        TextFieldType.PASSWORD -> {
            var showPassword by remember { mutableStateOf(false) }
            val passwordIcon: ComposableContent = remember(showPassword, value) {
                {
                    AnimatedVisibility(
                        visible = !value.isNullOrEmpty(),
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {
                        CTIcon(
                            icon = IconSpec.VectorIcon(
                                imageVector = if (showPassword) CTIconPack.EyeClose else CTIconPack.EyeOpen,
                                contentDescription = null,
                            ),
                            color = CTTheme.color.textOnSurface,
                            onClick = { showPassword = !showPassword },
                            size = Dimens.IconSize.Medium,
                        )
                    }
                }
            }

            BasicTextField(
                value = value.orEmpty(),
                modifier = modifier
                    .background(colors.backgroundColor(enabled).value, shape)
                    .indicatorLine(enabled, isError, interactionSource, colors)
                    // Override min size()
                    .defaultMinSize(
                        minWidth = Dimens.Size.textFieldMinWidth,
                        minHeight = Dimens.Size.textFieldMinHeight,
                    ),
                onValueChange = onValueChange,
                enabled = enabled,
                textStyle = mergedTextStyle,
                cursorBrush = SolidColor(colors.cursorColor(isError).value),
                visualTransformation = if (showPassword) visualTransformation else PasswordVisualTransformation(),
                keyboardOptions = inputType.getKeyboardOption(imeAction),
                keyboardActions = keyboardActions,
                interactionSource = interactionSource,
                singleLine = singleLine,
                maxLines = maxLines,
                decorationBox = @Composable { innerTextField ->
                    // places leading icon, text field with label and placeholder, trailing icon
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = value.orEmpty(),
                        visualTransformation = visualTransformation,
                        innerTextField = innerTextField,
                        label = {
                            labelText?.let { safeLabel ->
                                CTTextView(text = safeLabel)
                            }
                        },
                        leadingIcon = leadingIcon,
                        trailingIcon = passwordIcon,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        contentPadding = contentPadding,
                    )
                }
            )
        }
    }
}
