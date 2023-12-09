package com.benoitmanhes.cacheautresor.screen.home.news.section

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.benoitmanhes.designsystem.theme.CTTheme
import com.google.accompanist.pager.HorizontalPagerIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EliteSection(
    pagerState: PagerState,
    eliteCard: List<EliteCardState>,
) {
    val indicatorColor by animateColorAsState(
        targetValue = if (pagerState.currentPage == 0) CTTheme.color.cacheFound else CTTheme.color.cacheOwned,
        label = "indicator color",
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.extraSmall),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = eliteCard.size,
            activeColor = indicatorColor,
            inactiveColor = CTTheme.color.placeholder,
        )
        HorizontalPager(
            state = pagerState,
            pageSpacing = CTTheme.spacing.medium,
            contentPadding = PaddingValues(horizontal = CTTheme.spacing.large),
            beyondBoundsPageCount = 1,
            pageSize = PageSize.Fill,
            pageCount = eliteCard.count(),
            pageContent = { page ->
                eliteCard.getOrNull(page)?.Content()
            }
        )
    }
}

object EliteSection {
    private const val contentType: String = "EliteSection"

    @OptIn(ExperimentalFoundationApi::class)
    fun item(
        scope: LazyListScope,
        pagerState: PagerState,
        eliteCard: List<EliteCardState>,
    ) {
        scope.item(
            contentType = contentType,
            key = contentType,
        ) {
            EliteSection(pagerState = pagerState, eliteCard = eliteCard)
        }
    }
}
