package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.designsystem.utils.TextSpec

fun String.textSpec(): TextSpec = TextSpec.RawString(this)
