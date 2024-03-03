package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache

import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.row.MapRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.StickerRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.TextRowPickerState
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.section.DraftPropertiesSectionState
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.section.DraftStepSectionState

data class EditCacheViewModelState(
    val bottomBar: BottomActionBarState? = null,
    val cacheName: TextRowPickerState? = null,
    val cacheType: StickerRowPickerState? = null,
    val initCoordinates: MapRowPickerState? = null,
    val stepSection: DraftStepSectionState? = null,
    val propertiesSection: DraftPropertiesSectionState? = null,
)
