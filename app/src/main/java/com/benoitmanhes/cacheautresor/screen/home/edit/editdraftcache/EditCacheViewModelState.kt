package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache

import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.row.StickerRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.TextRowPickerState

data class EditCacheViewModelState(
    val bottomBar: BottomActionBarState? = null,
    val cacheName: TextRowPickerState? = null,
    val cacheType: StickerRowPickerState? = null,
)
