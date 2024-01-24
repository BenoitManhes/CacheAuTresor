package com.benoitmanhes.cacheautresor.navigation.creation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.screen.home.edit.availablefinalplaces.AvailableFinalPlacesRoute

fun NavGraphBuilder.creationNavGraph(
    navController: NavHostController,
) {
    composable(EditCacheDestination.AvailableFinalPlaces.route) {
        AvailableFinalPlacesRoute(
            navigateBack = navController::popBackStack,
        )
    }
}