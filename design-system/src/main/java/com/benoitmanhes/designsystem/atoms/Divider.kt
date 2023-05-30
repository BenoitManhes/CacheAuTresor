package com.benoitmanhes.designsystem.atoms

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun CTDivider(
    modifier: Modifier = Modifier,
    color: Color = CTTheme.color.placeholder,
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = CTTheme.stroke.thin,
    )
}

fun LazyListScope.bbDivider(modifier: Modifier = Modifier) {
    item {
        CTDivider(modifier)
    }
}
