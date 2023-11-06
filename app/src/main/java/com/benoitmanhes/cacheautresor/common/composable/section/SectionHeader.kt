package com.benoitmanhes.cacheautresor.common.composable.section

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.common.compose.extensions.thenIf
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.common.compose.text.TextSpec

@Composable
fun SectionHeader(
    title: TextSpec,
    modifier: Modifier = Modifier,
    horizontalPadding: Boolean = true,
) {
    CTTextView(
        text = title,
        modifier = modifier.thenIf(horizontalPadding) {
            padding(horizontal = CTTheme.spacing.large)
        },
        style = CTTheme.typography.bodySmall,
        color = CTTheme.color.placeholder,
    )
}

object SectionHeader {
    private const val contentType: String = "SectionHeader"

    fun item(
        scope: LazyListScope,
        title: TextSpec,
        key: Any? = title.hashCode(),
        modifier: Modifier = Modifier,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            SectionHeader(
                title = title,
                modifier = modifier,
            )
        }
    }
}
