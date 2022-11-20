package com.benoitmanhes.designsystem.atoms

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.theme.colorScheme

@Composable
fun CTDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier,
        color = MaterialTheme.colorScheme.placeholder,
    )
}

fun LazyListScope.bbDivider() {
    item {
        CTDivider()
    }
}
