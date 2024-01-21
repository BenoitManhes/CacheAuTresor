package com.benoitmanhes.cacheautresor.screen.home.create.mycaches.available

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.screen.home.create.mycaches.MyCachesScreen

@Composable
fun MyCachesAvailableRoute(
    viewModel: MyCachesAvailableViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    MyCachesScreen(uiState)
}