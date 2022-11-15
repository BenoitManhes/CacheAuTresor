package com.benoitmanhes.cacheautresor.navigation.unauthenticated

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.navigation.AppDestination
import com.benoitmanhes.cacheautresor.screen.authentication.connection.ConnectionScreen

object ConnectionDestination : AppDestination {
    override val route: String = "connection"
}

fun NavGraphBuilder.connectionGraph(
    navigateToAccountCreation: (String) -> Unit,
    showErrorSnackBar: (errorMsg: String) -> Unit,
) {
    composable(ConnectionDestination.route) {
        ConnectionScreen(
            navigateToAccountCreation = navigateToAccountCreation,
            showErrorSnackBar = showErrorSnackBar,
        )
    }
}