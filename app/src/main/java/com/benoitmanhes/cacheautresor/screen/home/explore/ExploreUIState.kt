package com.benoitmanhes.cacheautresor.screen.home.explore

import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.uimodel.UICache

data class ExploreUIState(
    val currentPosition: Coordinates? = null,
    val mapPosition: Coordinates = Coordinates(45.76, 4.83),
    val isAccessPositionGranted: Boolean? = null,
    val caches: List<UICache> = emptyList(),
    val cacheSelected: UICache? = null,
)
