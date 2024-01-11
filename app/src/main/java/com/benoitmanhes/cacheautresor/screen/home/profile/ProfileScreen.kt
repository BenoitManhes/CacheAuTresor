package com.benoitmanhes.cacheautresor.screen.home.profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.designsystem.atoms.spacer.SpacerHuge
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed

@Composable
fun ProfileRoute(
    innerPadding: PaddingValues,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CTScreenWrapper(
        bottomPadding = with(LocalDensity.current) {
            innerPadding.calculateBottomPadding().toPx().toInt()
        },
    ) {
        uiState?.let { data ->
            ProfileScreen(
                uiState = data,
                modifier = Modifier
                    .navigationBarsPadding()
                    .statusBarsPadding()
                    .padding(innerPadding),
            )
        }
    }
}

@Composable
private fun ProfileScreen(
    uiState: ProfileViewModelState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = CTTheme.spacing.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Profile Card
        uiState.explorerCard.item(scope = this)

        SpacerHuge.item(this)

        uiState.logoutButton.item(
            scope = this,
            color = CTTheme.composed { color.critical },
        )
    }
}
