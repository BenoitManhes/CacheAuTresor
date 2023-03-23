package com.benoitmanhes.cacheautresor.navigation.authenticated

import androidx.compose.animation.ExperimentalAnimationApi
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
import com.benoitmanhes.designsystem.theme.CTTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AuthenticatedRoot(
    navController: NavHostController = rememberAnimatedNavController(),
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
        containerColor = CTTheme.color.rootBackground,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = HomeDestination.route,
        ) {
            mainGraph(
                showErrorSnackBar = showErrorSnackBar,
            )
        }
    }
}
