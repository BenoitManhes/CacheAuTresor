package com.benoitmanhes.cacheautresor.screen.home.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun ExploreListScreen(
    uiState: ExploreUIState,
    navigateToCacheDetail: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = ListTopPadding),
        contentPadding = PaddingValues(CTTheme.spacing.large),
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
    ) {
        uiState.caches.forEach { uiCache ->
            CacheBanner.item(
                scope = this,
                uiCache = uiCache,
                onClick = { navigateToCacheDetail(uiCache.cache.cacheId) },
            )
        }
    }
}

private val ListTopPadding: Dp = 128.dp
