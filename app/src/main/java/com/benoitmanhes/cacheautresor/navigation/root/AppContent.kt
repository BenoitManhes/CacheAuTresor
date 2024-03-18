package com.benoitmanhes.cacheautresor.navigation.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.screen.emptyscreen.CTEmptyScreen
import com.benoitmanhes.cacheautresor.navigation.authenticated.AuthenticatedRoot
import com.benoitmanhes.cacheautresor.navigation.root.holder.AppContentState
import com.benoitmanhes.cacheautresor.navigation.unauthenticated.UnAuthenticatedRoot
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ImageSpec
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppContent(
    viewModel: AppContentViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    val isDarkMode = false
    val context = LocalContext.current

    val appContentState by viewModel.appContentState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initialize(context, context.getString(R.string.locale_string))
    }

    CTTheme(darkTheme = isDarkMode) {
        val useDarkIcons = !isDarkMode
        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
            onDispose {}
        }

        val uiState = appContentState
        when (uiState) {
            AppContentState.Authenticated -> AuthenticatedRoot()
            AppContentState.UnAuthenticated -> UnAuthenticatedRoot()
            is AppContentState.Maintenance -> MaintenanceScreen(uiState)
            is AppContentState.ForceToUpgrade -> ForceUpgradeScreen(uiState)
            AppContentState.Idle -> {} // nothing
        }
    }
}

@Composable
private fun MaintenanceScreen(uiState: AppContentState.Maintenance) {
    CTEmptyScreen(
        illustration = ImageSpec.ResImage(R.drawable.illustr_campfire),
        message = uiState.message,
    )
}

@Composable
private fun ForceUpgradeScreen(uiState: AppContentState.ForceToUpgrade) {
    CTEmptyScreen(
        illustration = ImageSpec.ResImage(R.drawable.illustr_time_machine),
        message = uiState.message,
    )
}
