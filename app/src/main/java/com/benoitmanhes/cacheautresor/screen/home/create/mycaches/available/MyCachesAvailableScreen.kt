package com.benoitmanhes.cacheautresor.screen.home.create.mycaches.available

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.cacheautresor.screen.home.create.mycaches.MyCachesScreen

@Composable
fun MyCachesAvailableRoute(
    navigateToCacheDetail: (String) -> Unit,
    viewModel: MyCachesAvailableViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navigation by viewModel.navigateToCache.collectAsStateWithLifecycle()

    LaunchedEffect(navigation) {
        val navValue = navigation ?: return@LaunchedEffect
        navigateToCacheDetail(navValue)
        viewModel.consumeNavigation()
    }

    MyCachesScreen(uiState)
}
