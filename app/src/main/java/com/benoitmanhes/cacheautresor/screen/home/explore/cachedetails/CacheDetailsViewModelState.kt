package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.section.InstructionSectionState
import com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.section.NoteSectionState
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.CacheTypeSectionState
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.CartographerSectionState
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.section.CacheDetailHeaderState
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonState
import com.benoitmanhes.designsystem.molecule.card.InfoCardState
import com.benoitmanhes.designsystem.molecule.jauge.CTJaugeState
import com.benoitmanhes.designsystem.molecule.row.CTRowState
import com.benoitmanhes.designsystem.molecule.selector.TabSelectorState
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.domain.model.Coordinates

sealed interface CacheDetailsViewModelState {

    object Initialize : CacheDetailsViewModelState

    data class Data(
        val cacheColor: @Composable () -> Color,
        val headerState: CacheDetailHeaderState,
        val uiMarkers: List<UIMarker>,
        val fabButtonState: FabButtonState?,
        val bottomBarState: BottomActionBarState?,
        val tabSelectorState: TabSelectorState?,
        val difficultyJaugeState: CTJaugeState,
        val groundJaugeState: CTJaugeState,
        val sizeJaugeState: CTJaugeState,
        val infoCardState: InfoCardState?,
        val typeSectionState: CacheTypeSectionState,
        val cartographerSectionState: CartographerSectionState,
        val cacheCoordinates: Coordinates,
        val distanceText: TextSpec,
        val description: TextSpec,
        val characteristics: List<CTRowState>,
        val instructionsSectionState: InstructionSectionState,
        val noteSectionState: NoteSectionState,
    ) : CacheDetailsViewModelState {

        val page: Int get() = (tabSelectorState?.page ?: 0).coerceAtLeast(0)
    }

    data class Empty(
        val message: TextSpec,
    ) : CacheDetailsViewModelState
}
