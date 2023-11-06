package com.benoitmanhes.designsystem.atoms.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun CTTextMultiSize(
    firstText: TextSpec,
    secondText: TextSpec,
) {
    val firstTextRaw = firstText.string()
    val secondTextRaw = secondText.string()

    val value = buildAnnotatedString {
        withStyle(style = CTTheme.typography.header1.toSpanStyle()) {
            append(firstTextRaw)
        }
        withStyle(style = CTTheme.typography.captionBold.toSpanStyle()) {
            append(secondTextRaw)
        }
    }
    CTTextView(
        text = TextSpec.RawAnnotatedString(value),
    )
}

@Preview
@Composable
private fun PreviewCTTextMultiSize() {
    CTTheme {
        CTTextMultiSize(
            firstText = "180".textSpec(),
            secondText = "pts".textSpec(),
        )
    }
}
