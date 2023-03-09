package com.benoitmanhes.cacheautresor.screen.home.explore

import com.benoitmanhes.domain.model.Coordinates

data class ExploreUIState(
    val currentPosition: Coordinates? = null,
    val mapPosition: Coordinates = Coordinates(45.76, 4.83),
    val isAccessPositionGranted: Boolean? = null,
)
