package com.benoitmanhes.cacheautresor.navigation.explore

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.CacheDetailsRoute
import com.benoitmanhes.cacheautresor.screen.home.explore.editnote.EditNoteRoute

@OptIn(ExperimentalAnimationApi::class)
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
