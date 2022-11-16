package com.benoitmanhes.cacheautresor.ui.res.animation

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.ui.res.icons.AppIconPack
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconLogo
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun LoaderAnimation(
    vector: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()

    val angle: Float by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = PeriodMillis),
        ),
    )

    Icon(
        imageVector = vector,
        contentDescription = null,
        modifier = modifier
            .rotate(angle),
        tint = color,
    )
}

private const val PeriodMillis: Int = 2000

@Preview
@Composable
private fun PreviewLoaderAnimation() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LoaderAnimation(
                AppIconPack.IconLogo,
                AppTheme.colors.onSurface,
            )
        }
    }
}
