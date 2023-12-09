package com.benoitmanhes.designsystem.atoms.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.benoitmanhes.cacheautresor.designsystem.R
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.common.compose.text.TextSpec
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun CTMarkdownText(
    markdown: TextSpec,
    modifier: Modifier = Modifier,
    color: Color = CTTheme.color.onBackground,
    linkColor: Color = CTTheme.color.primary,
    style: TextStyle = CTTheme.typography.body,
    onLinkClicked: ((String) -> Unit)? = null,
) {
    markdown.string()?.let { value ->
        MarkdownText(
            markdown = value,
            modifier = modifier,
            color = color,
            linkColor = linkColor,
            style = style,
            onLinkClicked = onLinkClicked,
            fontResource = R.font.league_spartan_regular,
        )
    }
}
