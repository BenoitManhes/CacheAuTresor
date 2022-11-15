package com.benoitmanhes.cacheautresor.navigation.unauthenticated

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AuthNavigation(
    navController: NavHostController,
    showErrorSnackBar: (errorMsg: String) -> Unit,
    onNavigateBack: () -> Unit = remember(navController) { { navController.popBackStack() } },
) {

    val navigateToAccountCreation: (accountToken: String) -> Unit = remember {
        { accountToken -> navController.navigate(AccountCreationDestination.getRoute(accountToken)) }
    }

    NavHost(
        navController = navController,
        startDestination = ConnectionDestination.route,
    ) {
        connectionGraph(
            navigateToAccountCreation = navigateToAccountCreation,
            showErrorSnackBar = showErrorSnackBar,
        )
        accountCreationGraph(
            onNavigateBack = onNavigateBack,
            showSnackbar = showErrorSnackBar,
        )
    }
}