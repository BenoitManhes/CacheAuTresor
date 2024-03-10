package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftstep

import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.row.MapRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.TextRowPickerState
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftstep.section.EditDraftStepInstructionSection
import com.benoitmanhes.common.compose.text.TextSpec

data class EditDraftStepViewModelState(
    val topBarTitle: TextSpec,
    val stepCoordinates: MapRowPickerState? = null,
    val instructions: EditDraftStepInstructionSection? = null,
    val clue: TextRowPickerState? = null,
    val validationCode: TextRowPickerState? = null,
    val onClickDelete: (() -> Unit)? = null,
    val bottomBar: BottomActionBarState? = null,
)
