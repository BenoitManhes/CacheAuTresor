package com.benoitmanhes.designsystem.molecule

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.CTIcons
import com.benoitmanhes.designsystem.res.icons.iconpack.Favorite
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.UiConstants
import timber.log.Timber

@Composable
fun CTJauge(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = CTTheme.color.primary,
    stepCount: Int = UiConstants.Jauge.stepCount,
) {
    val borderColor = CTTheme.color.surface
    val backgroundColor = CTTheme.color.placeholder
    val icon = CTTheme.icon.Favorite

    icon.pat

    Canvas(
        modifier = modifier
            .size(Dimens.Size.jaugeSize)
            .then(modifier),
    ) {
        val borderStroke = Stroke(width = (size.width * UiConstants.Jauge.borderStrokeSizeRatio), cap = StrokeCap.Round)
        val fillStroke = Stroke(width = borderStroke.width * UiConstants.Jauge.fillStrokeRatio, cap = StrokeCap.Round)
        for (i in stepCount downTo 1) {
            val _progress = i / stepCount.toFloat()
            val progressOffset = if (i != stepCount) {
                -(borderStroke.width / (size.width * 5))
            } else 0f
            drawCircularIndicator(_progress + progressOffset, color = borderColor, stroke = borderStroke)
            drawCircularIndicator(_progress + progressOffset, color = backgroundColor, stroke = fillStroke, strokeRef = borderStroke)
        }
        drawOutline(
            outline = GenericShape {

            },
            color = color,
            style = borderStroke,
        )
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
private fun PreviewCTJauge() {
    CTTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CTJauge(
                progress = 0.4f, modifier = Modifier.background(Color.Cyan)
            )
        }
    }
}