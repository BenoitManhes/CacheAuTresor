package com.benoitmanhes.cacheautresor.screen.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.screen.explore.ExploreScreen
import com.benoitmanhes.cacheautresor.screen.profile.ProfileScreen

@Composable
fun HomeNavigation(navController: NavHostController, scaffoldPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = MainDestination.Explore.route
    ) {
        composable(MainDestination.News.route) {}
        composable(MainDestination.Explore.route) { ExploreScreen(Modifier.padding(bottom = scaffoldPadding.calculateBottomPadding())) }
        composable(MainDestination.Create.route) {}
        composable(MainDestination.Instruments.route) {}
        composable(MainDestination.Profile.route) { ProfileScreen(Modifier.padding(scaffoldPadding)) }
    }
}
