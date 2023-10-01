package com.benoitmanhes.domain.extension

import java.math.BigDecimal
import java.math.RoundingMode

fun Double.roundDecimal(decimal: Int): Double = BigDecimal(this).setScale(decimal, RoundingMode.HALF_EVEN).toDouble()
