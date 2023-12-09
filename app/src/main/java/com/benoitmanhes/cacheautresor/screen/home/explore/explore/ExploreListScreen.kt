package com.benoitmanhes.cacheautresor.screen.home.explore.explore

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
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = ListTopPadding),
        contentPadding = PaddingValues(CTTheme.spacing.large),
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
    ) {
        uiState.cacheList
            .forEach { uiCache ->
                uiCache.item(this)
            }
    }
}

private val ListTopPadding: Dp = 128.dp
