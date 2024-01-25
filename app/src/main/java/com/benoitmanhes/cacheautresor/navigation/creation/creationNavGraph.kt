package com.benoitmanhes.cacheautresor.navigation.creation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.screen.home.edit.availablefinalplaces.AvailableFinalPlacesRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.EditDraftCacheRoute

fun NavGraphBuilder.creationNavGraph(
    navController: NavHostController,
) {
    composable(EditCacheDestination.AvailableFinalPlaces.route) {
        AvailableFinalPlacesRoute(
            navigateBack = navController::popBackStack,
            navigateDraftCacheDetail = { draftCacheId ->
                navController.navigate(
                    route = EditCacheDestination.EditDraftCache.getRoute(draftCacheId),
                ) {
                    popUpTo(EditCacheDestination.AvailableFinalPlaces.route) { inclusive = true }
                }
            },
        )
    }

    composable(EditCacheDestination.EditDraftCache.route) {
        EditDraftCacheRoute(
            navigateBack = navController::popBackStack,
        )
    }
}
