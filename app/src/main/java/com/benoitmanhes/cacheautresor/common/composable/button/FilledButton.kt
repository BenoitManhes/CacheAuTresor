package com.benoitmanhes.cacheautresor.common.composable.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.common.composable.textview.TextView
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun FilledButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary,
    textColor: Color = AppTheme.colors.onPrimary,
    enabled: Boolean = true,
    onClick: () -> Unit = { },
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = buttonColors(
            contentColor = textColor,
            containerColor = color,
            disabledContainerColor = AppTheme.colors.disable,
            disabledContentColor = AppTheme.colors.onDisable,
        ),
        shape = AppTheme.shape.mediumRoundedCornerShape,
    ) {
        TextView(
            text = text,
            style = AppTheme.typography.bodyBold,
            color = textColor,
        )
    }
}

@Preview
@Composable
private fun PreviewFilledButton() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(verticalArrangement = Arrangement.spacedBy(Dimens.Margin.medium)) {
                FilledButton(text = "Primary")
                FilledButton(text = "Secondary", color = AppTheme.colors.secondary)
                FilledButton(text = "Disable", enabled = false)
            }
        }
    }
}
