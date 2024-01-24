package com.benoitmanhes.cacheautresor.navigation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.navigation.creation.creationNavGraph
import com.benoitmanhes.cacheautresor.navigation.explore.ExploreDestination
import com.benoitmanhes.cacheautresor.navigation.explore.exploreNavGraph
import com.benoitmanhes.cacheautresor.screen.home.create.CreateRoute
import com.benoitmanhes.cacheautresor.screen.home.encyclopedia.EncyclopediaScreen
import com.benoitmanhes.cacheautresor.screen.home.explore.explore.ExploreRoute
import com.benoitmanhes.cacheautresor.screen.home.news.NewsRoute
import com.benoitmanhes.cacheautresor.screen.home.profile.ProfileRoute

@Composable
fun HomeNavigation(
    navController: NavHostController,
    scaffoldPadding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.Explore.route
    ) {
        composable(HomeDestination.News.route) { NewsRoute(scaffoldPadding) }
        composable(HomeDestination.Explore.route) {
            ExploreRoute(
                navigateToCacheDetail = { cacheSelectedId ->
                    navController.navigate(ExploreDestination.CacheDetails.getRoute(cacheSelectedId))
                },
                innerPadding = scaffoldPadding,
            )
        }
        composable(HomeDestination.Create.route) { CreateRoute(scaffoldPadding) }
        composable(HomeDestination.Encyclopedia.route) { EncyclopediaScreen(scaffoldPadding) }
        composable(HomeDestination.Profile.route) { ProfileRoute(scaffoldPadding) }

        exploreNavGraph(navController)
        creationNavGraph(navController)
    }
}
