package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

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
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.domain.model.Coordinates
import javax.annotation.concurrent.Immutable

sealed interface CacheDetailsViewModelState {

    object Initialize : CacheDetailsViewModelState

    @Immutable
    data class Data(
        val cacheColorTheme: CTColorTheme,
        val headerState: CacheDetailHeaderState,
        val uiMarkers: List<UIMarker>,
        val fabButtonState: FabButtonState?,
        val bottomBarState: BottomActionBarState?,
        val difficultyJaugeState: CTJaugeState,
        val groundJaugeState: CTJaugeState,
        val sizeJaugeState: CTJaugeState,
        val infoCardState: InfoCardState?,
        val typeSectionState: CacheTypeSectionState,
        val cartographerSectionState: CartographerSectionState,
        val cacheCoordinates: Coordinates,
        val distanceText: TextSpec?,
        val description: TextSpec,
        val characteristics: List<CTRowState>,
        val stepInstructions: List<InstructionSectionState>,
        val noteSectionState: NoteSectionState?,
        val initialPageIndex: Int,
    ) : CacheDetailsViewModelState {

        val pageCount: Int get() = stepInstructions.count() + 1
    }

    @Immutable
    data class Empty(
        val message: TextSpec,
    ) : CacheDetailsViewModelState
}
