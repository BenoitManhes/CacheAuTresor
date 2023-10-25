package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.screen.home.explore.CacheMarkerIcon
import com.benoitmanhes.domain.uimodel.UICacheDetails

internal fun UICacheDetails.getCacheMarker(): CacheMarkerIcon = when (status) {
    UICacheDetails.Status.Owned -> CacheMarkerIcon.Owner
    is UICacheDetails.Status.Found -> CacheMarkerIcon.Found
    is UICacheDetails.Status.Started -> cache.getCacheMarkerStarted()
    UICacheDetails.Status.Available -> cache.getCacheMarker()
}
