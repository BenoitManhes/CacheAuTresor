package com.benoitmanhes.cacheautresor.screen.home.explore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.designsystem.lab.Selector
import com.benoitmanhes.designsystem.lab.SelectorItem
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonType
import com.benoitmanhes.designsystem.molecule.button.fabiconbutton.FabIconButton
import com.benoitmanhes.designsystem.res.icons.iconpack.Filter
import com.benoitmanhes.designsystem.res.icons.iconpack.Search
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreRoute(
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var selectorScreenItem by remember { mutableStateOf(selectorItems.first()) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(PositionDefault, ZoomDefault)
    }
    val pagerState = rememberPagerState()

    LaunchedEffect(true) {
        viewModel.initLocationListener(context)
    }

    LaunchedEffect(selectorScreenItem) {
        val page = selectorItems.indexOf(selectorScreenItem)
        pagerState.animateScrollToPage(page)
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        HorizontalPager(
            pageCount = 2,
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            when (page) {
                0 -> {
                    ExploreMapScreen(
                        uiState = viewModel.uiState,
                        cameraPositionState = cameraPositionState,
                        updateMapPosition = viewModel::setMapPosition,
                        selectCache = viewModel::selectCache,
                        unselectCache = viewModel::unselectCache,
                    )
                }
                1 -> {
                    ExploreListScreen(
                        uiState = viewModel.uiState,
                    )
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
        FabIconButton(
            icon = IconSpec.VectorIcon(imageVector = CTTheme.icon.Search, contentDescription = null),
            onClick = { /*TODO*/ },
            type = FabButtonType.OUTLINED,
        )
        Selector(
            items = selectorItems,
            selectedItem = selectorScreenItem,
            onSelectedItem = {
                selectorScreenItem = it
            },
            modifier = Modifier
                .width(SelectorItemWidth * selectorItems.size),
        )
        FabIconButton(
            icon = IconSpec.VectorIcon(imageVector = CTTheme.icon.Filter, contentDescription = null),
            onClick = { /*TODO*/ },
            type = FabButtonType.OUTLINED,
        )
    }
}

private const val ZoomDefault: Float = 15f
private val PositionDefault: LatLng = LatLng(45.76, 4.83)
private val SelectorItemWidth: Dp = 72.dp

private val selectorItems: List<SelectorItem> = listOf(
    SelectorItem(TextSpec.Resources(R.string.exploreScreen_selectorItem_map)),
    SelectorItem(TextSpec.Resources(R.string.exploreScreen_selectorItem_list)),
)
