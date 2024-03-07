package com.benoitmanhes.cacheautresor.common.screen.pickjauge

import androidx.compose.runtime.Stable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.screen.pickjauge.composable.JaugePickerRowState
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.jauge.CTJaugeState

@Stable
interface PickJaugeUiState {
    val jaugeState: CTJaugeState
    val jaugeRow: List<JaugePickerRowState>
    val topBarTitle: TextSpec
    val bottomActionBar: BottomActionBarState
}
