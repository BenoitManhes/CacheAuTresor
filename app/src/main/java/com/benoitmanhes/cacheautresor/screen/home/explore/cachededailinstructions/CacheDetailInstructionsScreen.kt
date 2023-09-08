package com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.CacheDetailsViewModelState
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun CacheDetailInstructionsScreen(
    uiState: CacheDetailsViewModelState.Data,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(state = lazyListState) {
        SpacerLarge.item(this)

    }
}