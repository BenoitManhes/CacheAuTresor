package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.text.TextSpec

internal fun TextSpec?.orPlaceHolder(): TextSpec = this ?: TextSpec.Resources(R.string.common_noValue_placeHolder)
