package com.benoitmanhes.cacheautresor.common.composable.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benoitmanhes.cacheautresor.common.composable.textview.TextView
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun OutlineButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.onSurface,
    enabled: Boolean = true,
    onClick: () -> Unit = { },
) {
    val buttonColor = if (enabled) color else AppTheme.colors.disable
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        border = BorderStroke(Dimens.Stroke.thin, buttonColor),
        shape = AppTheme.shape.mediumRoundedCornerShape,
    ) {
        TextView(
            text = text,
            style = AppTheme.typography.bodyBold,
            color = buttonColor,
        )
    }
}

@Preview
@Composable
private fun PreviewOutlinedButton() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(verticalArrangement = Arrangement.spacedBy(Dimens.Margin.medium)) {
                OutlineButton(modifier = Modifier.width(128.dp), text = "Enabled")
                OutlineButton(modifier = Modifier.width(128.dp), text = "Disable", enabled = false)
            }
        }
    }
}
