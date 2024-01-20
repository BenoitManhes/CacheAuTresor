package com.benoitmanhes.cacheautresor.screen.home.create.mycaches.available

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.cacheautresor.screen.home.create.mycaches.MyCachesScreen
import com.benoitmanhes.cacheautresor.screen.home.create.mycaches.MyCachesViewModelState
import com.benoitmanhes.common.compose.extensions.textSpec
import timber.log.Timber

@Composable
fun MyCachesAvailableRoute(
    viewModel: MyCachesAvailableViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    MyCachesScreen(uiState)
}
