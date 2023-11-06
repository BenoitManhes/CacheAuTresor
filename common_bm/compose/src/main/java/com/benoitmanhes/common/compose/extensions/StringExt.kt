package com.benoitmanhes.common.compose.extensions

import com.benoitmanhes.common.compose.text.TextSpec

fun String.textSpec(): TextSpec = TextSpec.RawString(this)
