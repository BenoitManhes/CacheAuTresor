package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.domain.model.CacheUserStatus
import com.benoitmanhes.domain.model.Distance
import com.benoitmanhes.domain.uimodel.UIExploreCache
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.utils.DomainConstants
import javax.inject.Inject

class SortCacheUseCase @Inject constructor() : CTUseCase() {
    operator fun invoke(caches: List<UIExploreCache>): List<UIExploreCache> =
        caches
            .sortedBy {
                val distance = it.distance ?: Distance.INFINITE
                val distanceCoef = if (distance > DomainConstants.Explore.cacheSortingDistance) 100 else 0
                val statusCoef = when (it.userStatus) {
                    CacheUserStatus.Started -> 0f
                    CacheUserStatus.Available -> 1f
                    CacheUserStatus.Found -> 2f
                    CacheUserStatus.Owned -> 3f
                    CacheUserStatus.Locked -> 7f
                    CacheUserStatus.Hidden -> 8f
                }

                statusCoef + distanceCoef
            }
}
