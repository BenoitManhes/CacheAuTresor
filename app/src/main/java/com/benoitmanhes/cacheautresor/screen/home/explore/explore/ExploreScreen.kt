package com.benoitmanhes.cacheautresor.screen.home.explore.explore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.cacheautresor.BuildConfig
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.viewModel.LocationAccessView
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.spacer.SpacerMedium
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonType
import com.benoitmanhes.designsystem.molecule.button.fabiconbutton.FabIconButton
import com.benoitmanhes.designsystem.molecule.selector.CTSwitchSelector
import com.benoitmanhes.designsystem.molecule.selector.SelectorItem
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.uimodel.UIExploreCache

@Composable
fun ExploreRoute(
    navigateToCacheDetail: (String) -> Unit,
    innerPadding: PaddingValues,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val navigation by viewModel.navigation.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = navigation) {
        val navValue = navigation ?: return@LaunchedEffect
        when (navValue) {
            is ExploreNavigation.CacheDetail -> {
                navigateToCacheDetail(navValue.cacheId)
            }
        }
        viewModel.consumeNavigation()
    }

    CTScreenWrapper(
        bottomPadding = with(LocalDensity.current) {
            innerPadding.calculateBottomPadding().toPx().toInt()
        },
    ) {
        LocationAccessView(viewModel = viewModel)
        ExploreScreen(
            innerPadding = innerPadding,
            uiState = uiState,
            onMapPositionChange = viewModel::onMapPositionChange,
            onSelectCache = viewModel::selectCache,
            onUnSelectCache = viewModel::unselectCache,
            requestPermissionLocation = viewModel::requestLocation,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreScreen(
    innerPadding: PaddingValues,
    uiState: ExploreUIState,
    onMapPositionChange: (Coordinates) -> Unit,
    onSelectCache: (UIExploreCache) -> Unit,
    onUnSelectCache: () -> Unit,
    requestPermissionLocation: () -> Unit,
) {
    var page by rememberSaveable { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(
        pageCount = { 2 }
    )

    LaunchedEffect(page) {
        pagerState.animateScrollToPage(page)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            when (page) {
                0 -> {
                    ExploreMapScreen(
                        uiState = uiState,
                        updateMapPosition = onMapPositionChange,
                        selectCache = onSelectCache,
                        unselectCache = onUnSelectCache,
                        requestLocationPermission = requestPermissionLocation,
                    )
                }

                1 -> {
                    ExploreListScreen(uiState = uiState)
                }
            }
        }
    }

    Row(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = CTTheme.spacing.large, vertical = CTTheme.spacing.small)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (BuildConfig.DEBUG) {
            FabIconButton(
                icon = CTTheme.icon.Search,
                onClick = { /*TODO*/ },
                type = FabButtonType.OUTLINED,
            )
        } else {
            SpacerMedium()
        }
        CTSwitchSelector(
            items = selectorItems,
            selectedItem = selectorItems[page],
            onSelectedItem = {
                page = selectorItems.indexOf(it)
            },
            modifier = Modifier.width(SelectorItemWidth * selectorItems.size),
        )
        if (BuildConfig.DEBUG) {
            FabIconButton(
                icon = CTTheme.icon.Filter,
                onClick = { /*TODO*/ },
                type = FabButtonType.OUTLINED,
            )
        } else {
            SpacerMedium()
        }
    }
}

private val SelectorItemWidth: Dp = 72.dp

private val selectorItems: List<SelectorItem> = listOf(
    SelectorItem(TextSpec.Resources(R.string.exploreScreen_selectorItem_map)),
    SelectorItem(TextSpec.Resources(R.string.exploreScreen_selectorItem_list)),
)
