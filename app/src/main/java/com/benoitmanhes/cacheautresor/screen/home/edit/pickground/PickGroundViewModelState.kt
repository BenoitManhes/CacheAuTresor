package com.benoitmanhes.cacheautresor.screen.home.edit.pickground

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.screen.pickjauge.PickJaugeUiState
import com.benoitmanhes.cacheautresor.common.screen.pickjauge.composable.JaugePickerRowState
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.jauge.CTJaugeState

@Immutable
data class PickGroundViewModelState(
    override val jaugeState: CTJaugeState,
    override val jaugeRow: List<JaugePickerRowState>,
    override val topBarTitle: TextSpec,
    override val bottomActionBar: BottomActionBarState,
) : PickJaugeUiState

