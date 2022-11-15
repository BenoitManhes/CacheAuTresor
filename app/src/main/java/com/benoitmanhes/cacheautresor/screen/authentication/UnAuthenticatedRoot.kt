package com.benoitmanhes.cacheautresor.screen.authentication

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.benoitmanhes.cacheautresor.navigation.unauthenticated.AuthNavigation
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnAuthenticatedRoot(
    navController: NavHostController = rememberNavController(),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val showErrorSnackBar: (errorMsg: String) -> Unit = { errorMsg ->
        coroutineScope.launch {
            snackBarHostState.showSnackbar(message = errorMsg, withDismissAction = true)
        }
    }

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        containerColor = AppTheme.colors.background,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) {
        AuthNavigation(
            navController = navController,
            showErrorSnackBar = showErrorSnackBar,
        )
    }
}