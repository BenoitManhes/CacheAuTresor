package com.benoitmanhes.cacheautresor.navigation.unauthenticated

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.benoitmanhes.designsystem.theme.CTTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
        containerColor = CTTheme.color.rootBackground,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) {
        UnauthenticatedNavigation(
            navController = navController,
            showErrorSnackBar = showErrorSnackBar,
        )
    }
}
