package com.benoitmanhes.cacheautresor.screen.home.explore

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.cacheautresor.common.extensions.getIconCache
import com.benoitmanhes.cacheautresor.common.extensions.toDistanceText
import com.benoitmanhes.cacheautresor.common.extensions.toOneDecimalFormat
import com.benoitmanhes.cacheautresor.common.extensions.toSizeText
import com.benoitmanhes.designsystem.molecule.caches.CacheCard
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.designsystem.utils.extensions.getPrimaryColor
import com.benoitmanhes.domain.uimodel.UIExploreCache

@Composable
fun CacheBanner(
    uiExploreCache: UIExploreCache,
    color: Color = uiExploreCache.getPrimaryColor(),
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    CacheCard(
        icon = uiExploreCache.getIconCache(),
        color = color,
        creatorText = TextSpec.RawString(uiExploreCache.explorerName ?: "-"),
        titleText = TextSpec.RawString(uiExploreCache.cache.title),
        difficultyText = TextSpec.RawString(uiExploreCache.cache.difficulty.toOneDecimalFormat()),
        groundText = TextSpec.RawString(uiExploreCache.cache.ground.toOneDecimalFormat()),
        sizeText = uiExploreCache.cache.size.toSizeText(),
        distanceText = uiExploreCache.distance?.toDistanceText(),
        modifier = modifier,
        onClick = onClick,
    )
}

object CacheBanner {
    fun item(
        scope: LazyListScope,
        uiExploreCache: UIExploreCache,
        modifier: Modifier = Modifier,
        key: Any = uiExploreCache.cache.cacheId,
        onClick: () -> Unit,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            CacheBanner(
                uiExploreCache = uiExploreCache,
                modifier = modifier,
                onClick = onClick,
            )
        }
    }

    private const val contentType: String = "CacheBanner"
}
