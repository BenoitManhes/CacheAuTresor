package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache

import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.row.MapRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.StickerRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.TextRowPickerState
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState

data class EditCacheViewModelState(
    val bottomBar: BottomActionBarState? = null,
    val cacheName: TextRowPickerState? = null,
    val cacheType: StickerRowPickerState? = null,
    val initCoordinates: MapRowPickerState? = null,
    val intermediaryStep: List<MapRowPickerState> = emptyList(),
    val addIntermediaryStepButton: PrimaryButtonState? = null,
    val finalStep: MapRowPickerState? = null,
)
