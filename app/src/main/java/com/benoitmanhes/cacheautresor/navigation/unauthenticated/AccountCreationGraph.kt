package com.benoitmanhes.cacheautresor.navigation.unauthenticated

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.navigation.AppDestination
import com.benoitmanhes.cacheautresor.screen.authentication.accountcreation.AccountCreationScreen

fun NavGraphBuilder.accountCreationGraph(
    onNavigateBack: () -> Unit,
    showSnackbar: (String) -> Unit,
) {
    composable(AccountCreationDestination.route) {
        AccountCreationScreen(onNavigateBack = onNavigateBack, showSnackbar = showSnackbar)
    }
}

object AccountCreationDestination : AppDestination {
    const val accountTokenArgument: String = "accountToken"

    override val route: String = "accountDestination/{$accountTokenArgument}"

    fun getRoute(tokenAccount: String): String = route.replace("{$accountTokenArgument}", tokenAccount)
}
