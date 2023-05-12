package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.cacheautresor.common.CTMapView
import com.benoitmanhes.cacheautresor.common.composable.bottomsheet.BottomSheetState
import com.benoitmanhes.cacheautresor.common.composable.bottomsheet.CTBottomSheet
import com.benoitmanhes.cacheautresor.common.extensions.getCacheMarker
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.rememberMapViewWithLifecycle
import com.benoitmanhes.cacheautresor.screen.home.explore.refresh
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBar
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.uimodel.UICacheDetails
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import kotlin.math.roundToInt

@Composable
fun CacheDetailsRoute(
    onNavigateBack: () -> Unit,
    viewModel: CacheDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CacheDetailsScreen(
        onNavigateBack = onNavigateBack,
        data = (uiState as? CacheDetailsUIState.Data),
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CacheDetailsScreen(
    onNavigateBack: () -> Unit,
    data: CacheDetailsUIState.Data?,
) {
    val context: Context = LocalContext.current
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

    LaunchedEffect(mapViewState) {
        mapViewState.setupMap(AppConstants.Map.defaultLocation)
    }

    LaunchedEffect(data?.uiCacheDetails?.cache) {
        mapViewState.overlays.removeAll(markerFolder.items)
        markerFolder.items.clear()
        data?.uiCacheDetails?.let { uiCacheDetails ->
            markerFolder.add(uiCacheDetails.getOSMMarker(context, mapViewState))
            mapViewState.controller.setCenter(uiCacheDetails.cache.coordinates.toGeoPoint())
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
        CTBottomSheet(
            header = {
            },
            body = {
            },
            swipeableState = swipeableState,
        )
    }

    CTTopBar(
        modifier = Modifier.statusBarsPadding(),
        navAction = CTNavAction.Back(onNavigateBack),
    )
}

private fun MapView.setupMap(
    center: Coordinates,
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
}

private fun UICacheDetails.getOSMMarker(
    context: Context,
    mapViewState: MapView,
): Marker = Marker(mapViewState).apply {
    position = cache.coordinates.toGeoPoint()
    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
    icon = cache.getCacheMarker().getDrawable(
        isSelected = false,
        context = context,
    )
}
