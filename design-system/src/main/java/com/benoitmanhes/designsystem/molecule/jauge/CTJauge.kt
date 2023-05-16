package com.benoitmanhes.designsystem.molecule.jauge

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.UiConstants

@Composable
fun CTJauge(
    step: CTJaugeStep,
    modifier: Modifier = Modifier,
) {
    val progress = remember { Animatable(0f) }
    LaunchedEffect(step) {
        progress.animateTo(
            targetValue = step.progress,
            animationSpec = tween(durationMillis = UiConstants.Jauge.progressAnimDurationMillis)
        )
    }
    BoxWithConstraints(
        modifier = modifier.size(width = Dimens.Jauge.size, height = 65.dp)
    ) {
        CircularJauge(
            progress = progress.value,
        )
    }
}

@Preview
@Composable
private fun PreviewCTJauge() {
    CTTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column() {
                CTJaugeStep.values().forEach {
                    CTJauge(it, modifier = Modifier.border(CTTheme.stroke.thin, Color.Red))
                }
            }
        }
    }
}