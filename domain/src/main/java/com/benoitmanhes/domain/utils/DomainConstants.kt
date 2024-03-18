package com.benoitmanhes.domain.utils

import com.benoitmanhes.domain.model.Distance
import com.benoitmanhes.domain.model.Distance.Companion.kms
import com.benoitmanhes.domain.model.Distance.Companion.meters
import kotlin.math.pow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

object DomainConstants {
    object News {
        const val numberExplorerElite: Int = 3
    }

    object Cache {
        const val ptsWinPenaltyCoef: Float = 0.5f
        val unlockingAvailableDistance: Distance = 25.meters
        val initialCoordinatesDistanceMin: Distance = 10.meters
        val finalCoordinatesDistanceMin: Distance = 50.meters
    }

    object Explore {
        val cacheSortingDistance: Distance = 10.kms
    }

    object EditCache {
        const val maxCacheNameLenght: Int = 36
        const val maxCrewNameLenght: Int = 36
    }

    object Location {
        const val coordinatesDecimals: Int = 5
        val coordinatesApproximateError: Double = 10.0.pow(-coordinatesDecimals.toDouble())
    }

    object Loading {
        val minLoadingDuration: Duration = 750.milliseconds
    }
}
