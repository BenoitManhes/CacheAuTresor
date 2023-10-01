package com.benoitmanhes.cacheautresor.common.extensions

import java.text.DecimalFormat

fun Number.toOneDecimalFormat(): String = roundOneDecimalFormat.format(this)

private val roundOneDecimalFormat: DecimalFormat = DecimalFormat("#.#")
