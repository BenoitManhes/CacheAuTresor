package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.cacheautresor.common.CTMapView
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBar
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.rememberMapViewWithLifecycle
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.CacheDetailInstructionsScreen
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.CacheDetailRecapScreen
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.section.CacheDetailHeader
import com.benoitmanhes.cacheautresor.screen.home.explore.refresh
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.designsystem.atoms.spacer.SpacerMedium
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.button.fabbutton.CTFabButton
import com.benoitmanhes.designsystem.molecule.loading.CTLoadingView
import com.benoitmanhes.designsystem.molecule.selector.CTTabSelector
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBar
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.LocalColor
import com.benoitmanhes.designsystem.utils.AnimatedNullableVisibility
import com.benoitmanhes.domain.model.Coordinates
import kotlinx.coroutines.launch
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import kotlin.math.roundToInt

@Composable
fun CacheDetailsRoute(
    onNavigateBack: () -> Unit,
    viewModel: CacheDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CompositionLocalProvider(
        LocalColor provides LocalColor.current.copy(
            primaryColor = (uiState as? CacheDetailsViewModelState.Data)?.cacheColor?.invoke(),
        )
    ) {
        CTScreenWrapper {
            CacheDetailsScreen(
                onNavigateBack = onNavigateBack,
                uiState = uiState,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CacheDetailsScreen(
    onNavigateBack: () -> Unit,
    uiState: CacheDetailsViewModelState,
) {
    val context: Context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val mapViewState = rememberMapViewWithLifecycle()
    val bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState)
    val recapLazyListState = rememberLazyListState()
    val instructionsLazyListState = rememberLazyListState()
    val markerFolder = remember { FolderOverlay() }

    val data = uiState as? CacheDetailsViewModelState.Data

    LaunchedEffect(mapViewState) {
        mapViewState.setupMap(AppConstants.Map.defaultLocation) {
            coroutineScope.launch {
                bottomSheetState.partialExpand()
            }
        }
    }

    LaunchedEffect(data?.uiMarkers) {
        mapViewState.overlays.removeAll(markerFolder.items)
        markerFolder.items.clear()
        data?.uiMarkers?.forEach { uiMarker ->
            markerFolder.add(uiMarker.getOSMMarker(context, mapViewState))
        }
        data?.uiMarkers?.firstOrNull()?.let { uiMarker ->
            mapViewState.controller.setCenter(uiMarker.coordinates.toGeoPoint())
        }

        mapViewState.overlays.addAll(markerFolder.items)
        mapViewState.refresh()
    }

    CTMapView(
        modifier = Modifier
            .fillMaxWidth(),
        mapViewState = mapViewState,
    )

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column {
            CTTopBar(
                navAction = CTNavAction.Back(onNavigateBack),
            )
            SpacerMedium()

            Box(modifier = Modifier.weight(1f)) {
                BottomSheetScaffold(
                    scaffoldState = bottomSheetScaffoldState,
                    sheetPeekHeight = AppDimens.CacheDetail.bottomSheetHeaderHeight,
                    sheetContent = {
                        Column(
                            modifier = Modifier.background(CTTheme.color.background),
                        ) {
                            // Header
                            data?.headerState?.let {
                                CacheDetailHeader(uiState.headerState) {
                                    coroutineScope.launch {
                                        bottomSheetState.expand()
                                    }
                                }
                            }

                            // Body
                            when (uiState) {
                                is CacheDetailsViewModelState.Data -> {
                                    DataContent(
                                        uiState = uiState,
                                        recapLazyListState = recapLazyListState,
                                        instructionsLazyListState = instructionsLazyListState,
                                    )
                                }

                                CacheDetailsViewModelState.Initialize -> {
                                    InitContent()
                                }

                                is CacheDetailsViewModelState.Empty -> {
                                    EmptyContent(uiState = uiState)
                                }
                            }
                        }
                    },
                    sheetDragHandle = null,
                    content = {},
                )
            }

            data?.bottomBarState?.let { _bottomBarState ->
                BottomActionBar(
                    state = _bottomBarState,
                )
            }
        }

        AnimatedNullableVisibility(
            value = data?.fabButtonState,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(CTTheme.spacing.extraLarge),
            visible = { bottomSheetState.targetValue != SheetValue.PartiallyExpanded },
            enter = fadeIn(),
            exit = fadeOut(),
        ) { buttonState ->
            CTFabButton(
                state = buttonState,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DataContent(
    uiState: CacheDetailsViewModelState.Data,
    recapLazyListState: LazyListState,
    instructionsLazyListState: LazyListState,
) {
    val pageCount = remember(uiState.tabSelectorState?.items) {
        uiState.tabSelectorState?.items?.size ?: 1
    }
    val pagerState = rememberPagerState()

    LaunchedEffect(uiState.page) {
        pagerState.animateScrollToPage(uiState.page)
    }

    Column(
        Modifier
            .fillMaxSize()
            .animateContentSize(),
    ) {
        uiState.tabSelectorState?.let {
            CTTabSelector(
                tabSelectorState = it,
                modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
            )
            SpacerMedium()
        }
        Divider()

        HorizontalPager(
            pageCount = pageCount,
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            when (page) {
                0 -> {
                    CacheDetailRecapScreen(
                        uiState = uiState,
                        lazyListState = recapLazyListState,
                    )
                }

                1 -> {
                    CacheDetailInstructionsScreen(
                        uiState = uiState,
                        lazyListState = instructionsLazyListState,
                    )
                }
            }
        }
    }
}

@Composable
private fun InitContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CTLoadingView()
    }
}

@Composable
private fun EmptyContent(uiState: CacheDetailsViewModelState.Empty) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CTTextView(
            text = uiState.message,
            style = CTTheme.typography.body,
            color = CTTheme.color.placeholder,
        )
    }
}

private fun MapView.setupMap(
    center: Coordinates,
    hideBottomSheet: () -> Unit,
) { // Parameters
    setTileSource(TileSourceFactory.MAPNIK)
    setMultiTouchControls(true)
    zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
    controller.setZoom(AppConstants.Map.myLocationZoom)
    controller.setCenter(center.toGeoPoint())
    maxZoomLevel = AppConstants.Map.maxZoom
    minZoomLevel = AppConstants.Map.minZoom
    isVerticalMapRepetitionEnabled = false

    setScrollableAreaLimitLatitude(AppConstants.Map.areaLimitLatNorth, AppConstants.Map.areaLimitLatSouth, 0)

    // Setup my location
    val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), this).apply {
        enableMyLocation()
    }
    overlays.add(locationOverlay)

    // OnTap listener
    val mapEventOverlay = MapEventsOverlay(object : MapEventsReceiver {
        override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
            hideBottomSheet()
            return false
        }

        override fun longPressHelper(p: GeoPoint?): Boolean = false
    })
    overlays.add(mapEventOverlay)
}

private val bottomSheetAnimation: AnimationSpec<Float> = tween(durationMillis = 400)
