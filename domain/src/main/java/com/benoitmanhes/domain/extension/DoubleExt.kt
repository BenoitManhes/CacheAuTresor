package com.benoitmanhes.domain.extension

import com.benoitmanhes.domain.utils.DomainConstants
import java.math.BigDecimal
import java.math.RoundingMode

fun Double.roundDecimal(decimal: Int): Double = BigDecimal(this).setScale(decimal, RoundingMode.HALF_EVEN).toDouble()
fun Double.roundCoordinates(): Double = roundDecimal(DomainConstants.Location.coordinatesDecimals)
