package com.benoitmanhes.designsystem.atoms

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.theme.colorScheme
import com.benoitmanhes.designsystem.theme.stroke

@Composable
fun CTDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.placeholder,
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = MaterialTheme.stroke.thin,
    )
}

fun LazyListScope.bbDivider() {
    item {
        CTDivider()
    }
}
