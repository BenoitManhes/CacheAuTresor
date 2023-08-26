package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.screen.home.explore.CacheMarker
import com.benoitmanhes.domain.uimodel.UICacheDetails

internal fun UICacheDetails.getCacheMarker(): CacheMarker = when (userStatus) {
    UICacheDetails.Status.Owned -> CacheMarker.Owner
    UICacheDetails.Status.Found -> CacheMarker.Found
    else -> cache.getCacheMarker()
}
