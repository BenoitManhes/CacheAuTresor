package com.benoitmanhes.cacheautresor.navigation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.navigation.animation.DestinationAnimation
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
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
        startDestination = HomeDestination.Explore.route,
        enterTransition = {
            DestinationAnimation.getEnterTransitionFromRoute(targetState.destination.route)(this)
        },
        exitTransition = {
            DestinationAnimation.getExitTransitionFromRoute(targetState.destination.route)(this)
        },
        popEnterTransition = {
            DestinationAnimation.getPopEnterTransitionFromRoute(initialState.destination.route)(this)
        },
        popExitTransition = {
            DestinationAnimation.getPopExitTransitionFromRoute(initialState.destination.route)(this)
        },
    ) {
        composable(HomeDestination.News.route) {
            NewsRoute(innerPadding = scaffoldPadding)
        }
        composable(HomeDestination.Explore.route) {
            ExploreRoute(
                navigateToCacheDetail = { cacheSelectedId ->
                    navController.navigate(ExploreDestination.CacheDetails.getRoute(cacheSelectedId))
                },
                innerPadding = scaffoldPadding,
            )
        }
        composable(HomeDestination.Create.route) {
            CreateRoute(
                innerPadding = scaffoldPadding,
                navigateToEditDraftCache = { draftCacheId ->
                    navController.navigate(route = EditCacheDestination.EditDraftCache.getRoute(draftCacheId))
                },
                navigateToCacheDetail = { cacheId ->
                    navController.navigate(route = ExploreDestination.CacheDetails.getRoute(cacheId))
                }
            )
        }
        composable(HomeDestination.Encyclopedia.route) { EncyclopediaScreen(scaffoldPadding) }
        composable(HomeDestination.Profile.route) { ProfileRoute(scaffoldPadding) }

        exploreNavGraph(navController)
        creationNavGraph(navController)
    }
}
