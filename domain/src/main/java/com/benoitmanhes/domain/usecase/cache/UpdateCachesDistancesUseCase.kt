package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.uimodel.UIExploreCache
import com.benoitmanhes.domain.usecase.common.CalculateDistanceUseCase
import javax.inject.Inject

class UpdateCachesDistancesUseCase @Inject constructor(
    private val calculateDistanceUseCase: CalculateDistanceUseCase,
) {
    operator fun invoke(currentLocation: Coordinates, uiExploreCaches: List<UIExploreCache>): List<UIExploreCache> = uiExploreCaches.map { uiCache ->
        uiCache.copy(
            distance = calculateDistanceUseCase(currentLocation, uiCache.cache.coordinates),
        )
    }
}
