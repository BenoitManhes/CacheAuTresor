package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache

import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.row.MapRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.StickerRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.TextRowPickerState
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.section.DraftPropertiesSectionState
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.section.DraftStepSectionState
import com.benoitmanhes.common.compose.text.TextSpec

data class EditCacheViewModelState(
    val bottomActionBar: BottomActionBarState? = null,
    val cacheName: TextRowPickerState? = null,
    val cacheType: StickerRowPickerState? = null,
    val initCoordinates: MapRowPickerState? = null,
    val stepSection: DraftStepSectionState? = null,
    val propertiesSection: DraftPropertiesSectionState? = null,
    val descriptionSection: TextRowPickerState? = null,
    val unlockInstructions: TextRowPickerState? = null,
    val unlockCode: TextRowPickerState? = null,
    val personalNotes: TextSpec = TextSpec.RawString(""),
) {

    private val generalSection: List<Any?> = listOf(
        cacheName,
        cacheType,
        initCoordinates
    )

    private val characteristicsSection: List<Any?> = listOf(
        propertiesSection,
        descriptionSection,
    )

    private val unlockingSection: List<Any?> = listOf(
        unlockInstructions,
        unlockCode,
    )

    val showGeneralSection: Boolean = generalSection.any { it != null }
    val showStepsSection: Boolean = stepSection != null
    val showCharacteristicsSection: Boolean = characteristicsSection.any { it != null }
    val showLockSection: Boolean = unlockingSection.any { it != null }
}
