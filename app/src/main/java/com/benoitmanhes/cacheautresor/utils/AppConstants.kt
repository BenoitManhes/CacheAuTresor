package com.benoitmanhes.cacheautresor.utils

import com.benoitmanhes.domain.model.Coordinates
import kotlinx.coroutines.flow.SharingStarted
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

object AppConstants {

    object Flow {
        val DefaultSharingStarted: SharingStarted
            get() = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000, replayExpirationMillis = 5_000)
    }

    object Loading {
        val animationVisibilityDuration: Duration = 150.milliseconds
    }

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
        const val smallMapZoom: Double = 18.0
        const val maxZoom: Double = 22.0
        const val minZoom: Double = 3.5
        const val myLocationZoom: Double = 18.0
        const val areaLimitLatNorth: Double = 82.0
        const val areaLimitLatSouth: Double = -82.0
        const val markerTouchMargin: Int = 12
    }

    object ViewModel {
        const val defaultStopTimeOut: Long = 5000
    }
}
