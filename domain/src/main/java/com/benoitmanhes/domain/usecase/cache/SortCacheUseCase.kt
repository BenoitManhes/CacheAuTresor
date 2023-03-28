package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.domain.uimodel.UICache
import javax.inject.Inject

class SortCacheUseCase @Inject constructor() {
    operator fun invoke(caches: List<UICache>): List<UICache> =
        caches.sortedBy { it.distance ?: Double.MAX_VALUE }
}
