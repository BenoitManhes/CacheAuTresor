package com.benoitmanhes.cacheautresor.navigation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.screen.home.explore.ExploreScreen
import com.benoitmanhes.cacheautresor.screen.home.profile.ProfileScreen

@Composable
fun HomeNavigation(navController: NavHostController, scaffoldPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.Explore.route
    ) {
        composable(HomeDestination.News.route) {}
        composable(HomeDestination.Explore.route) { ExploreScreen(Modifier.padding(bottom = scaffoldPadding.calculateBottomPadding())) }
        composable(HomeDestination.Create.route) {}
        composable(HomeDestination.Instruments.route) {}
        composable(HomeDestination.Profile.route) { ProfileScreen(Modifier.padding(scaffoldPadding)) }
    }
}
