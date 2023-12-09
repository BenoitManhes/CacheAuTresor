package com.benoitmanhes.cacheautresor.screen.home.explore.explore

sealed interface ExploreNavigation {
    data class RequestLocation(val openSettings: Boolean) : ExploreNavigation
    data class CacheDetail(val cacheId: String) : ExploreNavigation
}
