package com.benoitmanhes.cacheautresor.screen.home.edit.pickinitcoordinates

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.card.CoordinatesCardState
import com.benoitmanhes.cacheautresor.common.screen.pickcoordinates.PickCoordinatesUiState
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.domain.model.ForbiddenZone

@Immutable
data class PickInitCoordinatesViewModelState(
    override val forbiddenPlaces: List<ForbiddenZone>,
    override val bottomActionBar: BottomActionBarState,
    override val currentCoordinates: CoordinatesCardState,
    override val uiMarker: UIMarker? = null,
) : PickCoordinatesUiState {
    override val otherMarkers: List<UIMarker> = emptyList()
}
