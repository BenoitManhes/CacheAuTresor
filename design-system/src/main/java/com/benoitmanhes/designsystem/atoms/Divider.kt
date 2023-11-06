package com.benoitmanhes.designsystem.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
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

@Composable
fun CTVerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = CTTheme.color.placeholder,
) {
    Box(
        modifier = modifier
            .width(CTTheme.stroke.thin)
            .background(color),
    )
}

fun LazyListScope.dividerItem(
    modifier: Modifier = Modifier,
    color: @Composable () -> Color,
) {
    item {
        CTDivider(
            modifier = modifier,
            color = color(),
        )
    }
}
