package com.benoitmanhes.cacheautresor.navigation.explore

import com.benoitmanhes.cacheautresor.navigation.CTDestination

sealed class ExploreDestination(
    override val route: String,
) : CTDestination {

    object CacheDetails : ExploreDestination(
        route = "cache-details",
    )
}