package com.benoitmanhes.domain.utils

import kotlin.math.pow

object DomainConstants {
    object Location {
        const val coordinatesDecimals: Int = 5
        val coordinatesApproximateError: Double = 10.0.pow(-coordinatesDecimals.toDouble())
    }
}
