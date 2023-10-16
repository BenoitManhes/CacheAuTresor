package com.benoitmanhes.domain.utils

import kotlin.math.pow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

object DomainConstants {
    object Cache {
        const val ptsWinPenaltyCoef: Float = 0.5f
    }

    object Location {
        const val coordinatesDecimals: Int = 5
        val coordinatesApproximateError: Double = 10.0.pow(-coordinatesDecimals.toDouble())
    }

    object Loading {
        val minLoadingDuration: Duration = 750.milliseconds
    }
}
