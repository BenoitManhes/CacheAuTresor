package com.benoitmanhes.cacheautresor.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.screen.connection.ConnectionScreen
import com.benoitmanhes.cacheautresor.screen.home.HomeScreen
import com.benoitmanhes.cacheautresor.screen.main.holder.AuthenticatedState
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppContent(
    viewModel: AppContentViewModel = hiltViewModel(),
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

        // Remove MaterialYou tint
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background),
        ) {
            when (viewModel.authenticatedState) {
                AuthenticatedState.Authenticated -> HomeScreen()
                AuthenticatedState.UnAuthenticated -> ConnectionScreen()
                AuthenticatedState.Unknown -> {}
            }
        }
    }
}