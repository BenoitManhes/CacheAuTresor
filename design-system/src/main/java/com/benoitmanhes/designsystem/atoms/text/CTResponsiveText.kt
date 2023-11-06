package com.benoitmanhes.designsystem.atoms.text

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.utils.UiConstants

@Composable
fun CTResponsiveText(
    text: TextSpec?,
    minFontSize: TextUnit,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current,
    textAlign: TextAlign? = null,
    softWrap: Boolean = true,
    maxLines: Int = UiConstants.Text.MaxLineSize,
) {
    var textStyle by remember { mutableStateOf(style) }
    var readyToDraw by remember { mutableStateOf(false) }

    text?.value()?.let { _text ->
        Text(
            modifier = modifier
                .drawWithContent {
                    if (readyToDraw) drawContent()
                },
            text = _text,
            color = color,
            textAlign = textAlign,
            softWrap = softWrap,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            style = textStyle,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.didOverflowHeight && textStyle.fontSize > minFontSize) {
                    textStyle = textStyle.copy(
                        fontSize = textStyle.fontSize * UiConstants.Text.RatioFontSizeReductionResponsiveText,
                        lineHeight = if (textStyle.lineHeight != TextUnit.Unspecified) {
                            textStyle.lineHeight * UiConstants.Text.RatioLineHeightReductionResponsiveText
                        } else {
                            textStyle.lineHeight
                        },
                    )
                } else {
                    readyToDraw = true
                }
            },
        )
    }
}
