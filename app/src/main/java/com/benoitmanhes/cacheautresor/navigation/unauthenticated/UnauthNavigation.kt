package com.benoitmanhes.cacheautresor.navigation.unauthenticated

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.benoitmanhes.cacheautresor.navigation.animation.DestinationAnimation

@Composable
fun UnauthenticatedNavigation(
    navController: NavHostController,
    showErrorSnackBar: (errorMsg: String) -> Unit,
    onNavigateBack: () -> Unit = remember(navController) { { navController.popBackStack() } },
) {
    val navigateToAccountCreation: (accountToken: String) -> Unit = remember {
        {
                accountToken ->
            navController.navigate(AccountCreationDestination.getRoute(accountToken))
        }
    }

    NavHost(
        navController = navController,
        startDestination = ConnectionDestination.route,
        enterTransition = {
            DestinationAnimation.getEnterTransitionFromRoute(targetState.destination.route)(this)
        },
        exitTransition = {
            DestinationAnimation.getExitTransitionFromRoute(targetState.destination.route)(this)
        },
        popEnterTransition = {
            DestinationAnimation.getPopEnterTransitionFromRoute(initialState.destination.route)(this)
        },
        popExitTransition = {
            DestinationAnimation.getPopExitTransitionFromRoute(initialState.destination.route)(this)
        },
    ) {
        connectionGraph(
            navigateToAccountCreation = navigateToAccountCreation,
            showErrorSnackBar = showErrorSnackBar,
            onNavigateBack = onNavigateBack,
        )
    }
}
