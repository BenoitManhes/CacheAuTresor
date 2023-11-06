package com.benoitmanhes.designsystem.molecule.card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ComposableContent

@Composable
fun CTCardLarge(
    modifier: Modifier = Modifier,
    content: ComposableContent,
) {
    Card(
        modifier = modifier
            .padding(CTTheme.spacing.large)
            .wrapContentSize(),
        shape = CTTheme.shape.large,
        elevation = CTTheme.elevation.large,
        backgroundColor = CTTheme.color.surface,
        contentColor = CTTheme.color.onSurface,
    ) {
        content()
    }
}

object CTCardLarge {
    private const val contentType: String = "CTCardLarge"

    fun item(
        scope: LazyListScope,
        key: Any?,
        modifier: Modifier = Modifier,
        content: ComposableContent,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            CTCardLarge(
                modifier = modifier,
                content = content,
            )
        }
    }
}
