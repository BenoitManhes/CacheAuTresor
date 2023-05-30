package com.benoitmanhes.designsystem.molecule.jauge

import com.benoitmanhes.designsystem.utils.UiConstants
import kotlin.math.max

object JaugeHelper {
    fun calculateProgressFromRate(rate: Float): Float {
        val rawProgress = rate / UiConstants.Jauge.stepCount
        return if (rawProgress < 1F) {
            max(rawProgress - UiConstants.Jauge.stepProgressDelta, 0f)
        } else {
            rawProgress
        }
    }
}
