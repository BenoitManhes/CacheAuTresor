package com.benoitmanhes.cacheautresor.common.composable.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.atoms.spacer.SpacerSmall
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ComposableContent
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun Section(
    title: TextSpec,
    modifier: Modifier = Modifier,
    content: ComposableContent,
) {
    Column(modifier) {
        CTTextView(
            text = title,
            modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
            style = CTTheme.typography.bodySmall,
            color = CTTheme.color.placeholder,
        )
        SpacerSmall()
        content()
    }
}

object Section {
    private const val contentType: String = "Section"

    fun item(
        scope: LazyListScope,
        title: TextSpec,
        key: Any? = title.hashCode(),
        modifier: Modifier = Modifier,
        content: ComposableContent,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            Section(
                title = title,
                modifier = modifier,
                content = content,
            )
        }
    }
}
