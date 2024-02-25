package com.benoitmanhes.cacheautresor.screen.home.edit.pickstepcoordinates

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.common.screen.pickcoordinates.PickCoordinatesScreen
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun PickStepCoordinatesRoute(
    navigateBack: () -> Unit,
    viewModel: PickStepCoordinatesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigation by viewModel.navigateBack.collectAsState()

    LaunchedEffect(navigation) {
        navigation ?: return@LaunchedEffect
        navigateBack()
        viewModel.consumeNavigation()
    }

    CTTheme(CTColorTheme.Cartography) {
        CTScreenWrapper {
            PickCoordinatesScreen(
                uiState = uiState,
                navigateBack = navigateBack,
                onClickEdit = viewModel::editCoordinatesManually,
                onLongClickMap = viewModel::updateCoordinates,
            )
        }
    }
}
