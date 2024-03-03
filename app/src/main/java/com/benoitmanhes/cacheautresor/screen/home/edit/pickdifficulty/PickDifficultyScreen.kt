package com.benoitmanhes.cacheautresor.screen.home.edit.pickdifficulty

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.common.screen.pickjauge.PickJaugeScreen
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun PickDifficultyRoute(
    navigateBack: () -> Unit,
    viewModel: PickDifficultyViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigation by viewModel.navigate.collectAsState()

    LaunchedEffect(navigation) {
        navigation ?: return@LaunchedEffect
        navigateBack()
        viewModel.consumeNavigation()
    }

    CTScreenWrapper(
        colorTheme = CTColorTheme.Cartography,
        darkStatusBarIcons = false,
    ) {
        PickJaugeScreen(
            uiState = uiState,
            navigateBack = navigateBack,
        )
    }
}
