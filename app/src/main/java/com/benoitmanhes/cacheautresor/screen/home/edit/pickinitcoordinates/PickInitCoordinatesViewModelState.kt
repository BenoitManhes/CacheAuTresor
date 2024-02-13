package com.benoitmanhes.cacheautresor.screen.home.edit.pickinitcoordinates

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.card.CoordinatesCardState
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.domain.model.Coordinates

@Immutable
data class PickInitCoordinatesViewModelState(
    val forbiddenPlaces: List<Coordinates>,
    val bottomActionBar: BottomActionBarState,
    val currentCoordinates: CoordinatesCardState,
    val uiMarker: UIMarker? = null,
)
