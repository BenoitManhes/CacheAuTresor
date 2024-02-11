package com.benoitmanhes.cacheautresor.screen.home.create.mycaches.creation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.screen.home.create.mycaches.MyCachesScreen

@Composable
fun MyCachesDraftRoute(
    navigateToEditDraftCache: (String) -> Unit,
    viewModel: MyCachesDraftViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigation by viewModel.navigation.collectAsState()

    LaunchedEffect(navigation) {
        val navValue = navigation ?: return@LaunchedEffect
        when (navValue) {
            is MyCachesDraftNavigation.EditDraftCache -> navigateToEditDraftCache(navValue.draftCacheId)
        }
        viewModel.consumeNavigation()
    }

    MyCachesScreen(uiState)
}
