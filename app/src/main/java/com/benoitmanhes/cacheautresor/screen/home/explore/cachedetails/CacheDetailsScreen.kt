package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.cacheautresor.common.CTMapView
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBar
import com.benoitmanhes.cacheautresor.common.composable.bottomsheet.BottomSheetState
import com.benoitmanhes.cacheautresor.common.composable.bottomsheet.CTBottomSheet
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.rememberMapViewWithLifecycle
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.section.CacheDetailHeader
import com.benoitmanhes.cacheautresor.screen.home.explore.refresh
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.cacheautresor.utils.AppDimens
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
        LocalColor provides LocalColor.current.copy(primaryColor = (uiState as? CacheDetailsViewModelState.Data)?.primaryColor)
    ) {
        CTScreenWrapper {
            CacheDetailsScreen(
                onNavigateBack = onNavigateBack,
                uiState = uiState,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CacheDetailsScreen(
    onNavigateBack: () -> Unit,
    uiState: CacheDetailsViewModelState,
) {
    val context: Context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val mapViewState = rememberMapViewWithLifecycle()
    val swipeableState = rememberSwipeableState(initialValue = BottomSheetState.HALF)
    val systemBarsPadding = WindowInsets.systemBars.asPaddingValues()
    val statusBarHeight = remember {
        systemBarsPadding.calculateTopPadding()
    }
    val markerFolder = remember { FolderOverlay() }

    val mapHeight = with(LocalDensity.current) {
        derivedStateOf {
            swipeableState.offset.value.roundToInt().toDp() + Dimens.Corner.large + statusBarHeight
        }
    }
    val data = uiState as? CacheDetailsViewModelState.Data

    LaunchedEffect(mapViewState) {
        mapViewState.setupMap(AppConstants.Map.defaultLocation) {
            coroutineScope.launch {
                swipeableState.animateTo(BottomSheetState.COLLAPSED, bottomSheetAnimation)
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
            .fillMaxWidth()
            .height(mapHeight.value),
        mapViewState = mapViewState,
    )

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column {
            Box(modifier = Modifier.weight(1f)) {
                CTBottomSheet(
                    header = {
                        data?.headerState?.let {
                            CacheDetailHeader(uiState.headerState) {
                                coroutineScope.launch {
                                    swipeableState.animateTo(BottomSheetState.EXPANDED, bottomSheetAnimation)
                                }
                            }
                        }
                    },
                    body = { scrollState ->
                        when (uiState) {
                            is CacheDetailsViewModelState.Data -> {
                                DataContent(uiState = uiState, scrollState = scrollState)
                            }

                            CacheDetailsViewModelState.Initialize -> {
                                InitContent()
                            }

                            is CacheDetailsViewModelState.Empty -> {
                                EmptyContent(uiState = uiState)
                            }
                        }
                    },
                    swipeableState = swipeableState,
                    peekHeight = AppDimens.CacheDetail.bottomSheetHeaderHeight,
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
            visible = { swipeableState.targetValue != BottomSheetState.COLLAPSED },
            enter = fadeIn() + slideInVertically { it * 2 },
            exit = fadeOut() + slideOutVertically { it * 2 },
        ) { buttonState ->
            CTFabButton(
                state = buttonState,
            )
        }

        CTTopBar(
            navAction = CTNavAction.Back(onNavigateBack),
        )
    }
}

@Composable
private fun DataContent(
    uiState: CacheDetailsViewModelState.Data,
    scrollState: LazyListState,
) {
    Column(
        Modifier
            .fillMaxSize()
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
    ) {
        uiState.tabSelectorState?.let {
            CTTabSelector(
                tabSelectorState = it,
                modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
            )
        }
        Divider()

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
