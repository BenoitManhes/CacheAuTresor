package com.benoitmanhes.cacheautresor.common.composable.switchs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.common.composable.textview.TextView
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun Selector(
    isActive: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary,
    contentColor: Color = AppTheme.colors.onPrimary,
) {
    Surface(
        modifier = modifier
            .padding(Dimens.Padding.switchText),
        color = color,
        shape = RoundedCornerShape(100),
    ) {
        Row {

        }
    }
}

@Preview
@Composable
private fun PreviewSwitchText() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Selector(true, onToggle = {})
        }
    }
}