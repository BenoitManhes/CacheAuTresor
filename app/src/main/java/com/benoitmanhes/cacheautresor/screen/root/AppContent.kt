package com.benoitmanhes.cacheautresor.screen.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.screen.authentication.UnAuthenticatedRoot
import com.benoitmanhes.cacheautresor.screen.home.HomeRoot
import com.benoitmanhes.cacheautresor.screen.root.holder.AuthenticatedState
import com.benoitmanhes.designsystem.theme.CTTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppContent(
    viewModel: AppContentViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    val isDarkMode = false

    CTTheme(darkTheme = isDarkMode) {

        val useDarkIcons = !isDarkMode
        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
            onDispose {}
        }

        when (viewModel.authenticatedState) {
            AuthenticatedState.Authenticated -> HomeRoot()
            AuthenticatedState.UnAuthenticated -> UnAuthenticatedRoot()
            AuthenticatedState.Unknown -> {}
        }
    }
}
