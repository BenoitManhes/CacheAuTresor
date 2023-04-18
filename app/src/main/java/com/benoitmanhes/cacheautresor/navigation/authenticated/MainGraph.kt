package com.benoitmanhes.cacheautresor.navigation.authenticated

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.benoitmanhes.cacheautresor.navigation.CTDestination
import com.benoitmanhes.cacheautresor.navigation.home.HomeRoot

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(
    showErrorSnackBar: (errorMsg: String) -> Unit,
) {
    composable(HomeDestination.route) {
        HomeRoot(showErrorSnackBar)
    }
}

object HomeDestination : CTDestination {
    override val route: String = "home"
}
