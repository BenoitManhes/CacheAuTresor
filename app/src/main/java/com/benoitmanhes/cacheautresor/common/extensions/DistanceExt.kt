package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.domain.model.Distance

fun Distance.toText(): TextSpec = if (kmsRounded > 0) {
    TextSpec.Resources(R.string.common_distanceInKilometers, kms.toOneDecimalFormat())
} else {
    TextSpec.Resources(R.string.common_distanceInMeters, meterRounded)
}
