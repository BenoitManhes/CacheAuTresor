package com.benoitmanhes.cacheautresor.screen.home.explore.explore.section

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.caches.CacheCard
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.utils.IconSpec

data class CacheBannerState(
    val cacheId: String,
    val icon: ComposeProvider<IconSpec>,
    val creatorText: TextSpec,
    val titleText: TextSpec,
    val difficultyText: TextSpec,
    val groundText: TextSpec,
    val sizeText: TextSpec,
    val distanceText: TextSpec?,
    val stickerText: TextSpec?,
    val colorTheme: CTColorTheme,
    val onClick: () -> Unit,
) {

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
    ) {
        CTTheme(colorTheme) {
            val cacheColor by animateColorAsState(
                CTTheme.color.primary,
                label = "cache color"
            )
            CacheCard(
                icon = icon(),
                creatorText = creatorText,
                titleText = titleText,
                difficultyText = difficultyText,
                groundText = groundText,
                sizeText = sizeText,
                distanceText = distanceText,
                stickerText = stickerText,
                color = cacheColor,
                modifier = modifier,
                onClick = onClick,
            )
        }
    }

    fun item(
        scope: LazyListScope,
        modifier: Modifier = Modifier,
        key: Any = cacheId,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            Content(modifier)
        }
    }

    private val contentType: String = "CacheBanner"
}
