package com.benoitmanhes.cacheautresor.navigation.explore

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.CacheDetailsRoute
import com.benoitmanhes.cacheautresor.screen.home.explore.editnote.EditNoteRoute

fun NavGraphBuilder.exploreNavGraph(
    navController: NavHostController,
) {
    composable(route = ExploreDestination.CacheDetails.route) {
        CacheDetailsRoute(
            onNavigateBack = { navController.popBackStack() },
            navigateToEditNote = { cacheId ->
                navController.navigate(ExploreDestination.EditNote.getRoute(cacheId))
            },
        )
    }
    composable(route = ExploreDestination.EditNote.route) {
        EditNoteRoute(
            onNavigateBack = { navController.popBackStack() },
        )
    }
}
