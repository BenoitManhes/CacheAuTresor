package com.benoitmanhes.cacheautresor.navigation.explore

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.CacheDetailsRoute

fun NavGraphBuilder.exploreNavGraph(
    navController: NavController,
) {
    composable(route = ExploreDestination.CacheDetails.route) { CacheDetailsRoute() }
}