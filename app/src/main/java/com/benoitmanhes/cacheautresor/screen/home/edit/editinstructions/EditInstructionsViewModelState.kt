package com.benoitmanhes.cacheautresor.screen.home.edit.editinstructions

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.domain.model.DraftCacheStep

@Immutable
data class EditInstructionsViewModelState(
    val bottomBar: BottomActionBarState,
    val rawText: String = "",
    val formattedText: TextSpec = TextSpec.RawString(""),
    val draftStep: DraftCacheStep? = null,
    val headerText: TextSpec? = null,
)
