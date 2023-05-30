package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.domain.model.CacheSize

fun CacheSize.toJaugeRate(): Float = when (this) {
    CacheSize.Micro -> 1f
    CacheSize.Small -> 2f
    CacheSize.Regular -> 3f
    CacheSize.Big -> 4f
    CacheSize.Undefined -> 0f
}
