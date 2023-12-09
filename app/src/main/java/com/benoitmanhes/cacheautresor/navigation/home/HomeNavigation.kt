package com.benoitmanhes.cacheautresor.navigation.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.benoitmanhes.cacheautresor.navigation.explore.ExploreDestination
import com.benoitmanhes.cacheautresor.navigation.explore.exploreNavGraph
import com.benoitmanhes.cacheautresor.screen.home.create.CreateScreen
import com.benoitmanhes.cacheautresor.screen.home.explore.explore.ExploreRoute
import com.benoitmanhes.cacheautresor.screen.home.encyclopedia.EncyclopediaScreen
import com.benoitmanhes.cacheautresor.screen.home.news.NewsRoute
import com.benoitmanhes.cacheautresor.screen.home.profile.ProfileRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeNavigation(
    navController: NavHostController,
    scaffoldPadding: PaddingValues,
) {
    AnimatedNavHost(
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
        composable(HomeDestination.Create.route) { CreateScreen(scaffoldPadding) }
        composable(HomeDestination.Encyclopedia.route) { EncyclopediaScreen(scaffoldPadding) }
        composable(HomeDestination.Profile.route) { ProfileRoute(scaffoldPadding) }

        exploreNavGraph(navController)
    }
}
