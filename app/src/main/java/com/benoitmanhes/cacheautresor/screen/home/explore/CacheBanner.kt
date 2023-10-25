package com.benoitmanhes.cacheautresor.screen.home.explore

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.extensions.getIcon
import com.benoitmanhes.cacheautresor.common.extensions.toDistanceText
import com.benoitmanhes.cacheautresor.common.extensions.toOneDecimalFormat
import com.benoitmanhes.cacheautresor.common.extensions.toSizeText
import com.benoitmanhes.designsystem.molecule.caches.CacheCard
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.LocalColor
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.designsystem.utils.extensions.getPrimaryColor
import com.benoitmanhes.domain.uimodel.UIExploreCache

@Composable
fun CacheBanner(
    uiExploreCache: UIExploreCache,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val cacheColor by animateColorAsState(uiExploreCache.getPrimaryColor(), label = "cache color")
    CompositionLocalProvider(
        LocalColor.provides(CTTheme.color.copy(primaryColor = cacheColor))
    ) {
        CacheCard(
            icon = uiExploreCache.cache.getIcon(),
            creatorText = TextSpec.RawString(uiExploreCache.explorerName ?: "-"),
            titleText = TextSpec.RawString(uiExploreCache.cache.title),
            difficultyText = TextSpec.RawString(uiExploreCache.cache.difficulty.toOneDecimalFormat()),
            groundText = TextSpec.RawString(uiExploreCache.cache.ground.toOneDecimalFormat()),
            sizeText = uiExploreCache.cache.size.toSizeText(),
            distanceText = uiExploreCache.distance?.toDistanceText()
                .takeIf { uiExploreCache.userStatus == UIExploreCache.CacheUserStatus.Available },
            stickerText = when (uiExploreCache.userStatus) {
                UIExploreCache.CacheUserStatus.Found -> uiExploreCache.ptsWin?.let {
                    TextSpec.Resources(R.string.cacheDetail_foundInfoCard_points, it)
                }

                UIExploreCache.CacheUserStatus.Started -> TextSpec.Resources(R.string.cacheDetail_cacheTypeSection_ongoing)
                else -> null
            },
            modifier = modifier,
            onClick = onClick,
        )
    }
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
