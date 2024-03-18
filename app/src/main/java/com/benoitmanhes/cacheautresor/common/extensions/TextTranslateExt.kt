package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.common.kotlin.TextTranslatable

fun TextTranslatable.toTextSpec(locale: String, defaultLocale: String = AppConstants.Locale.default): TextSpec =
    TextSpec.RawString(map[locale] ?: map[defaultLocale])
