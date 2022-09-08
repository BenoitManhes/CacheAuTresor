package com.benoitmanhes.cacheautresor.screen.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.benoitmanhes.cacheautresor.screen.connection.ConnectionScreen
import com.benoitmanhes.cacheautresor.screen.main.holder.AuthenticatedState
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppContent(
    viewModel: AppContentViewModel = viewModel(),
) {
    val systemUiController = rememberSystemUiController()

    AppTheme {

        val useDarkIcons = !isSystemInDarkTheme()
        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
            onDispose {}
        }

        when (viewModel.authenticatedState) {
            AuthenticatedState.Authenticated -> {}
            AuthenticatedState.UnAuthenticated -> ConnectionScreen()
            AuthenticatedState.Unknown -> {}
        }
    }
}