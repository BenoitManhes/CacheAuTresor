package com.benoitmanhes.cacheautresor.common.screen.pickcoordinates

import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.card.CoordinatesCardState
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.domain.model.ForbiddenZone

interface PickCoordinatesUiState {
    val forbiddenPlaces: List<ForbiddenZone>
    val bottomActionBar: BottomActionBarState
    val currentCoordinates: CoordinatesCardState
    val uiMarker: UIMarker?
    val otherMarkers: List<UIMarker>
}
