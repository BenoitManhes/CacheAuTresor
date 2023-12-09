package com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.section.InstructionSection
import com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.section.NoteSection
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.CacheDetailsViewModelState
import com.benoitmanhes.designsystem.atoms.dividerItem
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed

@Composable
fun CacheDetailInstructionsScreen(
    uiState: CacheDetailsViewModelState.Data,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(vertical = CTTheme.spacing.large),
    ) {
        InstructionSection.item(scope = this, state = uiState.instructionsSectionState)
        divider()
        NoteSection.item(scope = this, state = uiState.noteSectionState)
    }
}

private fun LazyListScope.divider() {
    dividerItem(
        modifier = Modifier.composed {
            padding(horizontal = CTTheme.spacing.large, vertical = CTTheme.spacing.large)
        },
        color = CTTheme.composed { color.disable },
    )
}
