package com.benoitmanhes.cacheautresor.utils

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object AppConstants {

    object Location {
        val defaultMinDurationInterval: Duration = 5.seconds
        const val defaultMinDistanceIntervalMeter: Float = 5f
    }
}