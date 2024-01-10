package com.benoitmanhes.cacheautresor.navigation.authenticated

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.navigation.CTDestination
import com.benoitmanhes.cacheautresor.navigation.home.HomeRoot

fun NavGraphBuilder.mainGraph() {
    composable(HomeDestination.route) {
        HomeRoot()
    }
}

object HomeDestination : CTDestination {
    override val route: String = "home"
}
