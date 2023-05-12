package com.benoitmanhes.cacheautresor.navigation.explore

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.CacheDetailsRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.exploreNavGraph(
    navController: NavHostController,
) {
    composable(route = ExploreDestination.CacheDetails.route) {
        CacheDetailsRoute(
            onNavigateBack = { navController.popBackStack() },
        )
    }
}
