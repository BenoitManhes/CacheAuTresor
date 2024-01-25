package com.benoitmanhes.cacheautresor.screen.home.news

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.cacheautresor.screen.home.news.section.EliteSection
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun NewsRoute(
    innerPadding: PaddingValues,
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CTScreenWrapper(
        bottomPadding = with(LocalDensity.current) {
            innerPadding.calculateBottomPadding().toPx().toInt()
        },
    ) {
        NewsScreen(
            uiState = uiState,
            modifier = Modifier
                .navigationBarsPadding()
                .statusBarsPadding()
                .padding(innerPadding),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NewsScreen(
    uiState: NewsViewModelState,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(
        pageCount = { uiState.eliteCards?.count() ?: 0 },
        initialPage = 0,
    )

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = CTTheme.spacing.large),
    ) {
        item {
            CTTextView(
                text = TextSpec.Resources(R.string.news_title),
                modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
                style = CTTheme.typography.header0,
            )
        }

        SpacerLarge.item(this)

        uiState.eliteCards?.let { eliteCards ->
            EliteSection.item(
                scope = this,
                pagerState = pagerState,
                eliteCard = eliteCards
            )
        }
    }
}
