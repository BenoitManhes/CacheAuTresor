package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.domain.uimodel.UIExploreCache
import javax.inject.Inject

class SortCacheUseCase @Inject constructor() {
    operator fun invoke(caches: List<UIExploreCache>): List<UIExploreCache> =
        caches.sortedBy { it.distance ?: Double.MAX_VALUE }
}
