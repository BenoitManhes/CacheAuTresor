package com.benoitmanhes.cacheautresor.screen.loading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.designsystem.atoms.spacer.SpacerMedium
import com.benoitmanhes.designsystem.theme.CTTheme
import kotlin.random.Random

private object LoadingScreenDimens {
    val loadingViewSize: Dp = 90.dp
    val compassBackgroundSize: Dp = 65.dp
    val aiguilleSize: DpSize = DpSize(
        width = 18.dp,
        height = 70.dp,
    )
}

@Composable
fun LoadingScreen(
    loadingViewModel: LoadingViewModel = hiltViewModel(),
) {
    val showLoading by loadingViewModel.isLoading.collectAsStateWithLifecycle()

    AnimatedVisibility(
        visible = showLoading,
        enter = fadeIn(tween()),
        exit = fadeOut(tween()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CTTheme.color.backgroundMask),
            contentAlignment = Alignment.Center,
        ) {
            CTLoadingView()
        }
    }
}

@Composable
private fun CTLoadingView(
    modifier: Modifier = Modifier,
) {
    var targetAngle by remember { mutableStateOf(0f) }
    val animatedAngle by animateFloatAsState(
        targetValue = targetAngle,
        animationSpec = tween(AppConstants.Loading.animationDuration.inWholeMilliseconds.toInt()),
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
                .size(LoadingScreenDimens.loadingViewSize),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_monochrome),
                contentDescription = null,
                modifier = Modifier
                    .size(LoadingScreenDimens.compassBackgroundSize)
                    .rotate(-animatedAngle),
                colorFilter = ColorFilter.tint(CTTheme.color.primary),
            )

            Image(
                painterResource(id = R.drawable.aiguille),
                contentDescription = null,
                modifier = Modifier
                    .size(LoadingScreenDimens.aiguilleSize)
                    .rotate(animatedAngle),
            )
        }
    }
}

@Composable
private fun CTLoadingView2(
    modifier: Modifier = Modifier,
    repeatMode: RepeatMode = RepeatMode.Restart,
) {
    val animatedAngle by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = AppConstants.Loading.animationDuration.inWholeMilliseconds.toInt(),
                easing = EaseInOutCubic,
            ),
            repeatMode = repeatMode,
        )
    )

    Surface(
        modifier = modifier
            .wrapContentSize(),
        color = CTTheme.color.surface,
        shape = CTTheme.shape.circle,
        border = BorderStroke(CTTheme.stroke.strong, CTTheme.color.primary),
    ) {
        Box(
            modifier = Modifier
                .size(LoadingScreenDimens.loadingViewSize),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_monochrome),
                contentDescription = null,
                modifier = Modifier
                    .size(LoadingScreenDimens.compassBackgroundSize)
                    .rotate(-animatedAngle),
                colorFilter = ColorFilter.tint(CTTheme.color.primary),
            )

            Image(
                painterResource(id = R.drawable.aiguille),
                contentDescription = null,
                modifier = Modifier
                    .size(LoadingScreenDimens.aiguilleSize)
                    .rotate(animatedAngle),
            )
        }
    }
}

private fun getNewAngle(): Float = if (Random.nextBoolean()) {
    Random.nextDouble(from = -AppConstants.Loading.maxRotationAngle, until = -AppConstants.Loading.minRotationAngle)
} else {
    Random.nextDouble(from = AppConstants.Loading.minRotationAngle, until = AppConstants.Loading.maxRotationAngle)
}.toFloat()

@Preview
@Composable
private fun LoadingScreenPreview() {
    CTTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CTTheme.color.backgroundMask),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CTLoadingView()
            SpacerMedium()
            CTLoadingView2()
            SpacerMedium()
            CTLoadingView2(repeatMode = RepeatMode.Reverse)
        }
    }
}
