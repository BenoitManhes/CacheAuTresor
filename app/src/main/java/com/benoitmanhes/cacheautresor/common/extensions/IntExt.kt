package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.text.TextSpec
import kotlin.math.abs
import kotlin.math.roundToInt

fun Double.toDistanceText(): TextSpec {
    val (rawDistance, distanceRes) = if (abs(this) < 1000) {
        this.roundToInt().toString() to R.string.common_distanceInMeters
    } else {
        (this / 1000f).toFloat().toOneDecimalFormat() to R.string.common_distanceInKilometers
    }
    return TextSpec.Resources(distanceRes, rawDistance)
}
