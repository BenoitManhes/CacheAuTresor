package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.section

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
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun CacheDetailInstructions(
    uiState: CacheDetailsViewModelState.Data,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(state = lazyListState) {
        (1..50).forEach { i ->
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(CTTheme.spacing.large)
                        .background(CTTheme.color.placeholder)
                ) {
                    CTTextView(text = TextSpec.RawString(i.toString()))
                }
            }
        }
    }
}