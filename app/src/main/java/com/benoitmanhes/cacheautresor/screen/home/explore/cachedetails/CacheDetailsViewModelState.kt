package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.section.CacheDetailHeaderState
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonState
import com.benoitmanhes.designsystem.molecule.selector.TabSelectorState
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

sealed interface CacheDetailsViewModelState {

    object Initialize : CacheDetailsViewModelState

    data class Data(
        val headerState: CacheDetailHeaderState,
        val uiMarkers: List<UIMarker>,
        val fabButtonState: FabButtonState?,
        val bottomBarState: BottomActionBarState?,
        val tabSelectorState: TabSelectorState?,
    ) : CacheDetailsViewModelState {
        val primaryColor: Color
            @Composable
            get() = CTTheme.color.primary
    }

    data class Empty(
        val message: TextSpec,
    ) : CacheDetailsViewModelState
}
