package com.benoitmanhes.cacheautresor.common.composable.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.atoms.spacer.SpacerSmall
import com.benoitmanhes.designsystem.utils.ComposableContent
import com.benoitmanhes.common.compose.text.TextSpec

@Composable
fun Section(
    title: TextSpec,
    modifier: Modifier = Modifier,
    content: ComposableContent,
) {
    Column(modifier) {
        SectionHeader(title = title)
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
