package com.benoitmanhes.cacheautresor.utils

import com.benoitmanhes.domain.model.Coordinates
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

object AppConstants {

    object Location {
        val defaultMinDurationInterval: Duration = 5.seconds
        const val defaultMinDistanceIntervalMeter: Float = 5f
    }

    object Map {
        val cameraAnimationDuration: Duration = 1.seconds
        val cameraAnimationDurationMin: Duration = 300.milliseconds
        val cameraMovingDelay: Duration = 500.milliseconds
        val defaultLocation: Coordinates = Coordinates(latitude = 45.760531, longitude = 4.833638)
        const val defaultZoom: Double = 15.0
        const val myLocationZoom: Double = 18.0
    }
}
