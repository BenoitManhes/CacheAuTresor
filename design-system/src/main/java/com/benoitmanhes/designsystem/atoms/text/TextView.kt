package com.benoitmanhes.designsystem.atoms.text

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.benoitmanhes.common.compose.text.TextSpec

@Composable
fun CTTextView(
    text: TextSpec?,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current,
    minLines: Int = 1,
    maxLine: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    text?.value()?.let { safeValue ->
        Text(
            text = safeValue,
            modifier = modifier,
            color = color,
            style = style,
            maxLines = maxLine,
            overflow = overflow,
            minLines = minLines,
            textAlign = textAlign,
        )
    }
}
