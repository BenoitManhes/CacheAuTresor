package com.benoitmanhes.cacheautresor.navigation.unauthenticated

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.navigation.CTDestination
import com.benoitmanhes.cacheautresor.screen.authentication.accountcreation.AccountCreationScreen
import com.benoitmanhes.cacheautresor.screen.authentication.connection.ConnectionScreen

fun NavGraphBuilder.connectionGraph(
    navigateToAccountCreation: (String) -> Unit,
    showErrorSnackBar: (errorMsg: String) -> Unit,
    onNavigateBack: () -> Unit,
) {
    composable(ConnectionDestination.route) {
        ConnectionScreen(
            navigateToAccountCreation = navigateToAccountCreation,
            showErrorSnackBar = showErrorSnackBar,
        )
    }

    composable(AccountCreationDestination.route) {
        AccountCreationScreen(onNavigateBack = onNavigateBack, showSnackbar = showErrorSnackBar)
    }
}

object ConnectionDestination : CTDestination {
    override val route: String = "connection"
}

object AccountCreationDestination : CTDestination {

    const val accountTokenArgument: String = "accountToken"

    override val route: String = "accountDestination/{$accountTokenArgument}"
    fun getRoute(tokenAccount: String): String = route.replace("{$accountTokenArgument}", tokenAccount)
}
