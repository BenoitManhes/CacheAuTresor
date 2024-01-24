package com.benoitmanhes.cacheautresor.screen.home.create

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.cacheautresor.screen.home.create.mycaches.available.MyCachesAvailableRoute
import com.benoitmanhes.cacheautresor.screen.home.create.mycaches.creation.MyCachesDraftRoute
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.spacer.SpacerMedium
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.selector.CTTabSelector
import com.benoitmanhes.designsystem.molecule.selector.SelectorItem
import com.benoitmanhes.designsystem.molecule.selector.TabSelectorState
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun CreateRoute(
    innerPadding: PaddingValues,
) {
    CTTheme(CTColorTheme.Cartography) {
        CTScreenWrapper(
            bottomPadding = with(LocalDensity.current) {
                innerPadding.calculateBottomPadding().toPx().toInt()
            },
        ) {
            CreateScreen(innerPadding)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CreateScreen(
    innerPadding: PaddingValues,
) {
    var selectedPage by rememberSaveable { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(
        pageCount = { creationTabItems.count() },
    )

    LaunchedEffect(selectedPage) {
        pagerState.animateScrollToPage(selectedPage)
    }

    val tabSelectorState = remember(selectedPage) {
        TabSelectorState(
            items = creationTabItems,
            selectedItem = creationTabItems[selectedPage],
            onSelectedItem = {
                selectedPage = creationTabItems.indexOf(it)
            }
        )
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(bottom = (innerPadding.calculateBottomPadding() - Dimens.Size.navigationBarCutoutGap).coerceAtLeast(0.dp))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
    ) {
        SpacerMedium()

        CTTextView(
            text = TextSpec.Resources(R.string.myCaches_header),
            modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
            style = CTTheme.typography.header0,
            color = CTTheme.color.onBackground,
        )

        CTTabSelector(
            tabSelectorState = tabSelectorState,
            modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
        )

        HorizontalPager(
            state = pagerState,
            beyondBoundsPageCount = 2,
            modifier = Modifier
                .fillMaxSize()
                .background(CTTheme.gradient.deepBlue, alpha = 0.75f),
            userScrollEnabled = false,
        ) { page ->
            when (page) {
                0 -> MyCachesDraftRoute()

                1 -> MyCachesAvailableRoute()
            }
        }
    }
}

private val creationTabItems: List<SelectorItem> = listOf(
    SelectorItem(text = TextSpec.Resources(R.string.myCaches_tabSelector_creation)),
    SelectorItem(text = TextSpec.Resources(R.string.myCaches_tabSelector_available)),
    //    SelectorItem(text = TextSpec.Resources(R.string.myCaches_tabSelector_archives)),
)
