package com.benoitmanhes.cacheautresor.screen.home.explore.explore.section

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.molecule.caches.CacheCard
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.LocalColor
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun CacheBanner(
    icon: IconSpec,
    creatorText: TextSpec,
    titleText: TextSpec,
    difficultyText: TextSpec,
    groundText: TextSpec,
    sizeText: TextSpec,
    distanceText: TextSpec?,
    stickerText: TextSpec?,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val cacheColor by animateColorAsState(
        color,
        label = "cache color"
    )
    CompositionLocalProvider(
        LocalColor.provides(CTTheme.color.copy(primaryColor = cacheColor))
    ) {
        CacheCard(
            icon = icon,
            creatorText = creatorText,
            titleText = titleText,
            difficultyText = difficultyText,
            groundText = groundText,
            sizeText = sizeText,
            distanceText = distanceText,
            stickerText = stickerText,
            modifier = modifier,
            onClick = onClick,
        )
    }
}

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
    val color: ComposeProvider<Color>,
    val onClick: () -> Unit,
) {

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
    ) {
        CacheBanner(
            icon = icon(),
            creatorText = creatorText,
            titleText = titleText,
            difficultyText = difficultyText,
            groundText = groundText,
            sizeText = sizeText,
            distanceText = distanceText,
            stickerText = stickerText,
            color = color(),
            modifier = modifier,
            onClick = onClick,
        )
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
