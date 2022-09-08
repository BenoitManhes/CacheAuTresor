package com.benoitmanhes.cacheautresor.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
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
    val isDarkMode = false

    AppTheme(darkTheme = isDarkMode) {

        val useDarkIcons = !isDarkMode
        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
            onDispose {}
        }

        // Remove Material You tint
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background),
        ) {
            when (viewModel.authenticatedState) {
                AuthenticatedState.Authenticated -> {}
                AuthenticatedState.UnAuthenticated -> ConnectionScreen()
                AuthenticatedState.Unknown -> {}
            }
        }
    }
}