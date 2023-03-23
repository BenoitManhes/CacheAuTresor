package com.benoitmanhes.cacheautresor.navigation.unauthenticated

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.benoitmanhes.cacheautresor.navigation.AppDestination
import com.benoitmanhes.cacheautresor.navigation.ctComposable
import com.benoitmanhes.cacheautresor.screen.authentication.accountcreation.AccountCreationScreen
import com.benoitmanhes.cacheautresor.screen.authentication.connection.ConnectionScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.connectionGraph(
    navigateToAccountCreation: (String) -> Unit,
    showErrorSnackBar: (errorMsg: String) -> Unit,
    onNavigateBack: () -> Unit,
) {
    ctComposable(ConnectionDestination.route) {
        ConnectionScreen(
            navigateToAccountCreation = navigateToAccountCreation,
            showErrorSnackBar = showErrorSnackBar,
        )
    }

    ctComposable(AccountCreationDestination.route) {
        AccountCreationScreen(onNavigateBack = onNavigateBack, showSnackbar = showErrorSnackBar)
    }
}

object ConnectionDestination : AppDestination {
    override val route: String = "connection"
}

object AccountCreationDestination : AppDestination {

    const val accountTokenArgument: String = "accountToken"

    override val route: String = "accountDestination/{$accountTokenArgument}"
    fun getRoute(tokenAccount: String): String = route.replace("{$accountTokenArgument}", tokenAccount)
}
