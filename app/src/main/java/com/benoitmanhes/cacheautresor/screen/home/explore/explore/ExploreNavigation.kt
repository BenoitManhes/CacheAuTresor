package com.benoitmanhes.cacheautresor.screen.home.explore.explore

sealed interface ExploreNavigation {
    data class CacheDetail(val cacheId: String) : ExploreNavigation
}
