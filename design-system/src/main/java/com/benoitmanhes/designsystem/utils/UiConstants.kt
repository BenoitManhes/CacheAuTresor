package com.benoitmanhes.designsystem.utils

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object UiConstants {

    object Jauge {
        const val startAngle: Float = 150f
        const val sweepAngle: Float = 360f * 2f / 3f
        const val stepCount: Int = 5
        const val stepProgressDelta: Float = 0.015f
        const val borderStrokeSizeRatio: Float = 0.15f
        const val fillStrokeRatio: Float = 0.66f
        const val progressAnimDurationMillis: Int = 1500
    }

    object Loading {
        const val minRotationAngle: Double = 180.0
        const val maxRotationAngle: Double = 540.0
        val animationDuration: Duration = 2.seconds
    }

    object Shape {
        const val mountainRatio: Float = 360f / 47f
    }

    object Text {
        const val MaxLineSize: Int = 3
        const val RatioFontSizeReductionResponsiveText: Float = 0.9f
        const val RatioLineHeightReductionResponsiveText: Float = 0.95f
    }
}
