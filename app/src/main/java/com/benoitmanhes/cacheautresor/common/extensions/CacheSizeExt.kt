package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.domain.model.CacheSize

fun CacheSize.toSizeText(): TextSpec {
    val sizeRes = when (this) {
        CacheSize.Micro -> R.string.cache_size_micro
        CacheSize.Small -> R.string.cache_size_small
        CacheSize.Regular -> R.string.cache_size_regular
        CacheSize.Big -> R.string.cache_size_big
        CacheSize.Undefined -> R.string.cache_size_undifined
    }
    return TextSpec.Resources(sizeRes)
}