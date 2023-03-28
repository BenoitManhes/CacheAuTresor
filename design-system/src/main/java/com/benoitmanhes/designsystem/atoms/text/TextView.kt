package com.benoitmanhes.designsystem.atoms.text

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.designsystem.utils.UiConstants

@Composable
fun CTTextView(
    text: TextSpec,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current,
    maxLine: Int = UiConstants.Text.MaxLineSize,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    text.value()?.let { safeValue ->
        Text(
            text = safeValue,
            modifier = modifier,
            color = color,
            style = style,
            maxLines = maxLine,
            overflow = overflow,
        )
    }
}
