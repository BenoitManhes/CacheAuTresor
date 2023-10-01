package com.benoitmanhes.designsystem.molecule.sticker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun CTSticker(
    label: TextSpec,
    modifier: Modifier = Modifier,
    color: Color = CTTheme.color.primary,
    textColor: Color = contentColorFor(backgroundColor = color),
) {
    Box(
        modifier = modifier
            .clip(CTTheme.shape.circle)
            .background(color)
            .padding(vertical = CTTheme.spacing.micro, horizontal = CTTheme.spacing.small),
    ) {
        CTTextView(
            text = label,
            color = textColor,
            style = CTTheme.typography.captionBold,
        )
    }
}

@Preview
@Composable
private fun PreviewCTSticker() {
    CTTheme {
        CTSticker(TextSpec.RawString("en cours ..."))
    }
}
