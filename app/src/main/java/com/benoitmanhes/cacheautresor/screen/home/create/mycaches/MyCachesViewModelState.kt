package com.benoitmanhes.cacheautresor.screen.home.create.mycaches

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.card.CacheCardState
import com.benoitmanhes.common.compose.text.TextSpec

@Immutable
sealed interface MyCachesViewModelState {
    data object Init : MyCachesViewModelState

    data class Empty(
        val message: TextSpec,
    ) : MyCachesViewModelState

    data class Data(
        val headerText: TextSpec,
        val cacheCards: List<CacheCardState>,
    ) : MyCachesViewModelState
}
