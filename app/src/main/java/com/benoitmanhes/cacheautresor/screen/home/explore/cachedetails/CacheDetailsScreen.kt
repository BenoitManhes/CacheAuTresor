package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import android.content.Context
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.CTMapView
import com.benoitmanhes.cacheautresor.common.composable.bottomsheet.BottomSheetState
import com.benoitmanhes.cacheautresor.common.composable.bottomsheet.CTBottomSheet
import com.benoitmanhes.cacheautresor.common.extensions.getCacheMarker
import com.benoitmanhes.cacheautresor.common.extensions.getColor
import com.benoitmanhes.cacheautresor.common.extensions.getIcon
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.rememberMapViewWithLifecycle
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.cacheautresor.screen.home.explore.refresh
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.spacer.SpacerExtraSmall
import com.benoitmanhes.designsystem.atoms.text.CTResponsiveText
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBar
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.LocalColor
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.uimodel.UICacheDetails
import kotlinx.coroutines.launch
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import kotlin.math.roundToInt

private val BottomSheetHeaderHeight: Dp = 64.dp

@Composable
fun CacheDetailsRoute(
    onNavigateBack: () -> Unit,
    viewModel: CacheDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val cachePrimaryColor = (uiState as? CacheDetailsUIState.Data)?.uiCacheDetails?.getColor()

    CompositionLocalProvider(LocalColor provides LocalColor.current.copy(primaryColor = cachePrimaryColor)) {
        CTScreenWrapper {
            CacheDetailsScreen(
                onNavigateBack = onNavigateBack,
                data = (uiState as? CacheDetailsViewModelState.Data),
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CacheDetailsScreen(
    onNavigateBack: () -> Unit,
    data: CacheDetailsUIState.Data?,
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

    LaunchedEffect(mapViewState) {
        mapViewState.setupMap(AppConstants.Map.defaultLocation) {
            coroutineScope.launch {
                swipeableState.animateTo(BottomSheetState.COLLAPSED, bottomSheetAnimation)
            }
        }
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
                Header(data = data) {
                    coroutineScope.launch {
                        swipeableState.animateTo(BottomSheetState.EXPANDED, bottomSheetAnimation)
                    }
                }
            },
            body = {
                cacheDetailsContent(
                    scope = this,
                    data = data,
                )
            },
            swipeableState = swipeableState,
            peekHeight = BottomSheetHeaderHeight,
        )
    }

    CTTopBar(
        modifier = Modifier.statusBarsPadding(),
        navAction = CTNavAction.Back(onNavigateBack),
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Header(
    data: CacheDetailsUIState.Data?,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(BottomSheetHeaderHeight),
        elevation = CTTheme.elevation.none,
        color = CTTheme.color.primary,
        onClick = onClick,
    ) {
        data?.uiCacheDetails?.cache?.let { cache ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CTResponsiveText(
                    text = TextSpec.RawString(cache.title),
                    minFontSize = CTTheme.typography.body.fontSize,
                    color = CTTheme.color.onPrimary,
                    style = CTTheme.typography.header1,
                    maxLines = 1,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CTIcon(
                        icon = cache.getIcon(),
                        size = Dimens.IconSize.Small,
                        color = CTTheme.color.onPrimary,
                    )
                    SpacerExtraSmall()
                    CTTextView(
                        text = cache.getTypeText(),
                        style = CTTheme.typography.caption,
                        color = CTTheme.color.onPrimary,
                    )
                }
            }
        }
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

private fun UICacheDetails.getOSMMarker(
    context: Context,
    mapViewState: MapView,
): Marker = Marker(mapViewState).apply {
    position = cache.coordinates.toGeoPoint()
    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
    icon = getCacheMarker().getDrawable(
        isSelected = false,
        context = context,
    )
}

private fun Cache.getTypeText(): TextSpec = when (this) {
    is Cache.Classical -> TextSpec.Resources(R.string.cache_type_classical)
    is Cache.Coop -> TextSpec.Resources(R.string.cache_type_coop)
    is Cache.Mystery -> TextSpec.Resources(R.string.cache_type_mystery)
    is Cache.Piste -> TextSpec.Resources(R.string.cache_type_piste)
}

private val bottomSheetAnimation: AnimationSpec<Float> = tween(durationMillis = 400)
