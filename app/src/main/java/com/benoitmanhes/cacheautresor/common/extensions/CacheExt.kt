package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.screen.home.explore.CacheMarker
import com.benoitmanhes.domain.model.Cache

internal fun Cache.getCacheMarker(): CacheMarker = when (this) {
    is Cache.Classical -> CacheMarker.Classical
    is Cache.Coop -> CacheMarker.Coop
    is Cache.Mystery -> CacheMarker.Mystery
    is Cache.Piste -> CacheMarker.Piste
}
