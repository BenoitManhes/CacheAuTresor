package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.domain.model.Distance
import com.benoitmanhes.domain.model.Distance.Companion.kms

fun Distance.toText(): TextSpec = if (this >= 1.kms) {
    TextSpec.Resources(R.string.common_distanceInKilometers, kms.toOneDecimalFormat())
} else {
    TextSpec.Resources(R.string.common_distanceInMeters, meterRounded)
}
