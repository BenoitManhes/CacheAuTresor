package com.benoitmanhes.cacheautresor.navigation.unauthenticated

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UnauthenticatedNavigation(
    navController: NavHostController,
    showErrorSnackBar: (errorMsg: String) -> Unit,
    onNavigateBack: () -> Unit = remember(navController) { { navController.popBackStack() } },
) {

    val navigateToAccountCreation: (accountToken: String) -> Unit = remember {
        { accountToken -> navController.navigate(AccountCreationDestination.getRoute(accountToken)) }
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = ConnectionDestination.route,
    ) {
        connectionGraph(
            navigateToAccountCreation = navigateToAccountCreation,
            showErrorSnackBar = showErrorSnackBar,
            onNavigateBack = onNavigateBack,
        )
    }
}
