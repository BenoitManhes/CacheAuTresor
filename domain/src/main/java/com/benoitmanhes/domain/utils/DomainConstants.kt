package com.benoitmanhes.domain.utils

import kotlin.math.pow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

object DomainConstants {
    object Location {
        const val coordinatesDecimals: Int = 5
        val coordinatesApproximateError: Double = 10.0.pow(-coordinatesDecimals.toDouble())
    }

    object Loading {
        val minLoadingDuration: Duration = 500.milliseconds
    }
}
