package com.benoitmanhes.designsystem.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun CTDivider(
    modifier: Modifier = Modifier,
    color: Color = CTTheme.color.strokeDivider,
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
    color: Color = CTTheme.color.strokeDivider,
    stroke: Dp = CTTheme.stroke.thin,
) {
    Box(
        modifier = modifier
            .width(stroke)
            .background(color),
    )
}

fun LazyListScope.dividerItem(
    modifier: Modifier = Modifier,
    color: @Composable () -> Color = { CTTheme.color.disable },
) {
    item {
        CTDivider(
            modifier = modifier,
            color = color(),
        )
    }
}
