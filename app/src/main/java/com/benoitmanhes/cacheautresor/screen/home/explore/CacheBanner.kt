package com.benoitmanhes.cacheautresor.screen.home.explore

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.common.extensions.getColor
import com.benoitmanhes.cacheautresor.common.extensions.getIconCache
import com.benoitmanhes.cacheautresor.common.extensions.toDistanceText
import com.benoitmanhes.cacheautresor.common.extensions.toOneDecimalFormat
import com.benoitmanhes.cacheautresor.common.extensions.toSizeText
import com.benoitmanhes.designsystem.molecule.caches.CacheCard
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.domain.uimodel.UICache

@Composable
fun CacheBanner(
    uiCache: UICache,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    CacheCard(
        icon = uiCache.getIconCache(),
        color = uiCache.getColor(),
        creatorText = TextSpec.RawString(uiCache.explorerName ?: "-"),
        titleText = TextSpec.RawString(uiCache.cache.title),
        difficultyText = TextSpec.RawString(uiCache.cache.difficulty.toOneDecimalFormat()),
        groundText = TextSpec.RawString(uiCache.cache.ground.toOneDecimalFormat()),
        sizeText = uiCache.cache.size.toSizeText(),
        distanceText = uiCache.distance?.toDistanceText(),
        modifier = modifier,
        onClick = onClick,
    )
}

object CacheBanner {
    fun item(
        scope: LazyListScope,
        uiCache: UICache,
        modifier: Modifier = Modifier,
        key: Any = uiCache.cache.cacheId,
        onClick: () -> Unit,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            CacheBanner(
                uiCache = uiCache,
                modifier = modifier,
                onClick = onClick,
            )
        }
    }

    private const val contentType: String = "CacheBanner"
}
