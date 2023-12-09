package com.benoitmanhes.cacheautresor.screen.home.explore.explore

import com.benoitmanhes.cacheautresor.screen.home.explore.explore.section.CacheBannerState
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.uimodel.UIExploreCache

data class ExploreUIState(
    val currentPosition: Coordinates? = null,
    val mapPosition: Coordinates = Coordinates(45.76, 4.83),
    val caches: List<UIExploreCache> = emptyList(),
    val cacheSelected: UIExploreCache? = null,
    val isLoading: Boolean = false,
    val cacheBanner: CacheBannerState? = null,
    val cacheList: List<CacheBannerState> = emptyList(),
)
