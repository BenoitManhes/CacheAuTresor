package com.benoitmanhes.cacheautresor.navigation.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.benoitmanhes.cacheautresor.navigation.explore.ExploreDestination
import com.benoitmanhes.cacheautresor.navigation.explore.exploreNavGraph
import com.benoitmanhes.cacheautresor.screen.home.create.CreateScreen
import com.benoitmanhes.cacheautresor.screen.home.explore.ExploreRoute
import com.benoitmanhes.cacheautresor.screen.home.instruments.InstrumentScreen
import com.benoitmanhes.cacheautresor.screen.home.news.NewsScreen
import com.benoitmanhes.cacheautresor.screen.home.profile.ProfileRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeNavigation(
    navController: NavHostController,
    scaffoldPadding: PaddingValues,
    showSnackbar: (String) -> Unit,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = HomeDestination.Explore.route
    ) {
        composable(HomeDestination.News.route) { NewsScreen() }
        composable(HomeDestination.Explore.route) {
            ExploreRoute(
                navigateToCacheDetail = { cacheSelectedId ->
                    navController.navigate(ExploreDestination.CacheDetails.getRoute(cacheSelectedId))
                },
                showSnackbar = showSnackbar,
                modifier = Modifier.padding(bottom = scaffoldPadding.calculateBottomPadding()),
            )
        }
        composable(HomeDestination.Create.route) { CreateScreen() }
        composable(HomeDestination.Instruments.route) { InstrumentScreen() }
        composable(HomeDestination.Profile.route) { ProfileRoute(scaffoldPadding) }

        exploreNavGraph(navController)
    }
}
