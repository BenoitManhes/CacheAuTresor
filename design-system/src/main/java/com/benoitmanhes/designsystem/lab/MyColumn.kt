package com.benoitmanhes.designsystem.lab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ComposableContent

@Composable
fun MyColumn(
    modifier: Modifier = Modifier,
    content: ComposableContent,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { mesurables, constraints ->

        val placeables = mesurables.map { measurable ->
            measurable.measure(constraints)
        }

        val height = placeables.sumOf { it.height }
        val width = placeables.maxOf { it.width }

        layout(width, height) {
            var y = 0

            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = y)
                y += placeable.height
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMyColumn() {
    CTTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
        }
    }
}