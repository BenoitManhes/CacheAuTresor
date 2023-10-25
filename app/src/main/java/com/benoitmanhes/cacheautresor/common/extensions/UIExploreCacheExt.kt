package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.screen.home.explore.CacheMarkerIcon
import com.benoitmanhes.domain.uimodel.UIExploreCache

internal fun UIExploreCache.getCacheMarker(): CacheMarkerIcon = when (userStatus) {
    UIExploreCache.CacheUserStatus.Owned -> CacheMarkerIcon.Owner
    UIExploreCache.CacheUserStatus.Found -> CacheMarkerIcon.Found
    UIExploreCache.CacheUserStatus.Started -> cache.getCacheMarkerStarted()
    else -> cache.getCacheMarker()
}
