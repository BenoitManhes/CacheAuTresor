package com.benoitmanhes.cacheautresor.screen.home.edit.pickunlockcode

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.screen.pickstring.PickStringScreen
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.CTColorTheme

@Composable
fun PickUnlockCodeRoute(
    navigateBack: () -> Unit,
    viewModel: PickUnlockCodeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigation by viewModel.navigateBack.collectAsState()

    LaunchedEffect(navigation) {
        navigation ?: return@LaunchedEffect
        navigateBack()
        viewModel.consumeNavigation()
    }

    CTScreenWrapper(
        darkStatusBarIcons = false,
        colorTheme = CTColorTheme.Cartography,
    ) {
        PickStringScreen(
            topBarTitle = TextSpec.Resources(R.string.pickLockCode_topBar_title),
            description = TextSpec.Resources(R.string.pickLockCode_message),
            uiState = uiState,
            navigateBack = navigateBack,
        )
    }
}
