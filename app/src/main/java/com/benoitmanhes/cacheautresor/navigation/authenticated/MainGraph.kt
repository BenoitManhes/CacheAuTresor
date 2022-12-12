package com.benoitmanhes.cacheautresor.navigation.authenticated

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.navigation.AppDestination
import com.benoitmanhes.cacheautresor.navigation.home.HomeRoot

fun NavGraphBuilder.mainGraph(
    showErrorSnackBar: (errorMsg: String) -> Unit,
) {
    composable(HomeDestination.route) {
        HomeRoot(showErrorSnackBar)
    }
}

object HomeDestination : AppDestination {
    override val route: String = "home"
}