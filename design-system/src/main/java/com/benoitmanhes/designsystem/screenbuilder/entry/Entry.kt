package com.benoitmanhes.designsystem.screenbuilder.entry

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

@Stable
interface Entry {
    val contentType: String
    val key: Any?

    @Composable
    fun Content()

    fun lazyItem(
        scope: LazyListScope,
    ) {
        scope.item(
            contentType = contentType,
            key = key
        ) {
            Content()
        }
    }
}
