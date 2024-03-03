package com.benoitmanhes.designsystem.molecule.jauge

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.shapes.JaugeShape
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.UiConstants

@Composable
internal fun CircularJauge(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color = CTTheme.color.primary,
) {
    val backgroundColor = CTTheme.color.surfaceLight

    Canvas(
        modifier = modifier
            .size(size)
            .clip(JaugeShape)
            .background(backgroundColor)
            .then(modifier),
    ) {
        val borderStroke = Stroke(
            width = (this.size.width * UiConstants.Jauge.borderStrokeSizeRatio * 0.54f),
            cap = StrokeCap.Round,
        )
        drawCircularIndicator(progress, color = color, stroke = borderStroke)
    }
}

private fun DrawScope.drawCircularIndicator(
    progress: Float,
    color: Color,
    stroke: Stroke,
    strokeRef: Stroke = stroke,
) { // To draw this circle we need a rect with edges that line up with the midpoint of the stroke.
    // To do this we need to remove half the stroke width from the total diameter for both sides.
    val diameterOffset = strokeRef.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = UiConstants.Jauge.startAngle,
        sweepAngle = UiConstants.Jauge.sweepAngle * progress,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = stroke,
    )
}

@Preview
@Composable
private fun PreviewCircularJauge() {
    CTTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column {
                CircularJauge(
                    progress = 0.4f,
                    size = Dimens.JaugeSize.Small.circleIndicatorSize,
                )
                SpacerLarge()
                CircularJauge(
                    progress = 0.66f,
                    size = Dimens.JaugeSize.Small.circleIndicatorSize,
                )
                SpacerLarge()
                CircularJauge(
                    progress = 1f,
                    size = Dimens.JaugeSize.Small.circleIndicatorSize,
                )
            }
        }
    }
}
