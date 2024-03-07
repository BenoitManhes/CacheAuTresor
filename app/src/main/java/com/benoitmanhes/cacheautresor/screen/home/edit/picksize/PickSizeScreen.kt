package com.benoitmanhes.cacheautresor.screen.home.edit.picksize

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.topbar.CTFilledTopBar
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun PickSizeRoute(
    navigateBack: () -> Unit,
    viewModel: PickSizeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigation by viewModel.navigate.collectAsState()

    LaunchedEffect(navigation) {
        navigation ?: return@LaunchedEffect
        navigateBack()
        viewModel.consumeNavigation()
    }

    CTScreenWrapper(
        darkStatusBarIcons = false,
        colorTheme = CTColorTheme.Cartography,
    ) {
        PickSizeScreen(
            uiState = uiState,
            navigateBack = navigateBack,
        )
    }
}

@Composable
private fun PickSizeScreen(
    uiState: PickSizeViewModelState,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CTFilledTopBar(
                title = TextSpec.Resources(R.string.pickSize_tobBar_title),
                navAction = CTNavAction.Back(navigateBack),
            )
        },
        backgroundColor = CTTheme.color.background,
        bottomBar = {
            uiState.bottomActionBar?.Content()
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(CTTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
        ) {
            uiState.sizeSelection.values.map { it.lazyItem(this) }
        }
    }
}
