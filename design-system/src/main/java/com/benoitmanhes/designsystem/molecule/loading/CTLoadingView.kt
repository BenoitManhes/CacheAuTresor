package com.benoitmanhes.designsystem.molecule.loading

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.benoitmanhes.cacheautresor.designsystem.R
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.UiConstants
import kotlin.random.Random

@Composable
fun CTLoadingView(
    modifier: Modifier = Modifier,
) {
    var targetAngle by remember { mutableStateOf(0f) }
    val animatedAngle by animateFloatAsState(
        targetValue = targetAngle,
        animationSpec = tween(UiConstants.Loading.animationDuration.inWholeMilliseconds.toInt()),
        finishedListener = {
            targetAngle += getNewAngle()
        }
    )

    LaunchedEffect(Unit) {
        targetAngle += getNewAngle()
    }

    Surface(
        modifier = modifier
            .wrapContentSize(),
        color = CTTheme.color.surface,
        shape = CTTheme.shape.circle,
        border = BorderStroke(CTTheme.stroke.strong, CTTheme.color.primary),
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.Loading.loadingViewSize),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_monochrome),
                contentDescription = null,
                modifier = Modifier
                    .size(Dimens.Loading.compassBackgroundSize)
                    .rotate(-animatedAngle),
                colorFilter = ColorFilter.tint(CTTheme.color.primary),
            )

            Image(
                painterResource(id = R.drawable.aiguille),
                contentDescription = null,
                modifier = Modifier
                    .size(Dimens.Loading.aiguilleSize)
                    .rotate(animatedAngle),
            )
        }
    }
}

private fun getNewAngle(): Float = if (Random.nextBoolean()) {
    Random.nextDouble(from = -UiConstants.Loading.maxRotationAngle, until = -UiConstants.Loading.minRotationAngle)
} else {
    Random.nextDouble(from = UiConstants.Loading.minRotationAngle, until = UiConstants.Loading.maxRotationAngle)
}.toFloat()
