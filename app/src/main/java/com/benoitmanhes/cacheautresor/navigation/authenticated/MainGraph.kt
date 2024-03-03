package com.benoitmanhes.cacheautresor.navigation.authenticated

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.navigation.CTDestination
import com.benoitmanhes.cacheautresor.navigation.home.HomeRoot

fun NavGraphBuilder.mainGraph() {
    composable(HomeRootDestination.route) {
        HomeRoot()
    }
}

object HomeRootDestination : CTDestination {
    override val route: String = "home-root"
}
