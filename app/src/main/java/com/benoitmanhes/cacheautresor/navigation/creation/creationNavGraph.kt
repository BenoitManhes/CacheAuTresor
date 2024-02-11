package com.benoitmanhes.cacheautresor.navigation.creation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.navigation.ctComposable
import com.benoitmanhes.cacheautresor.screen.home.edit.availablefinalplaces.AvailableFinalPlacesRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.EditDraftCacheRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.pickname.PickDraftCacheNameRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.picktype.PickTypeDraftCacheScreen

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

    ctComposable(EditCacheDestination.EditDraftCache.route) {
        EditDraftCacheRoute(
            navigateBack = navController::popBackStack,
            navigateToPickName = { draftCacheId ->
                navController.navigate(EditCacheDestination.PickNameDraftCache.getRoute(draftCacheId))
            },
            navigateToPickType = { draftCacheId ->
                navController.navigate(EditCacheDestination.PickTypeDraftCache.getRoute(draftCacheId))
            },
        )
    }

    ctComposable(EditCacheDestination.PickNameDraftCache.route) {
        PickDraftCacheNameRoute(
            navigateBack = navController::popBackStack,
        )
    }

    ctComposable(EditCacheDestination.PickTypeDraftCache.route) {
        PickTypeDraftCacheScreen(
            navigateBack = navController::popBackStack,
        )
    }
}
