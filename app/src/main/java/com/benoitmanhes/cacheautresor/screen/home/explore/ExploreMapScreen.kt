package com.benoitmanhes.cacheautresor.screen.home.explore

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.extensions.toModel
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonType
import com.benoitmanhes.designsystem.molecule.button.fabiconbutton.FabIconButton
import com.benoitmanhes.designsystem.res.icons.iconpack.Layer
import com.benoitmanhes.designsystem.res.icons.iconpack.Position
import com.benoitmanhes.designsystem.res.icons.iconpack.PositionCurrent
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.domain.extension.similar
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.uimodel.UICache
import kotlinx.coroutines.launch
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@Composable
internal fun ExploreMapScreen(
    uiState: ExploreUIState,
    updateMapPosition: (Coordinates) -> Unit,
    selectCache: (UICache) -> Unit,
    unselectCache: () -> Unit,
    navigateToCacheDetail: () -> Unit,
) {
    val mapViewState = rememberMapViewWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val markerFolder = remember { FolderOverlay() }

    LaunchedEffect(mapViewState) {
        mapViewState.setupMap(unselectCache = unselectCache, updateMapPosition = updateMapPosition)
    }

    LaunchedEffect(uiState.caches, uiState.cacheSelected) {
        mapViewState.overlays.removeAll(markerFolder.items)
        markerFolder.items.clear()

        uiState.caches.forEach { uiCache ->
            val marker = Marker(mapViewState).apply {
                position = uiCache.cache.coordinates.toGeoPoint()
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                icon = uiCache.getCacheMarker().getDrawable(
                    isSelected = uiState.cacheSelected == uiCache,
                    context = context,
                )
                setOnMarkerClickListener { _, _ ->
                    selectCache(uiCache)
                    true
                }
            }
            markerFolder.add(marker)
        }

        mapViewState.overlays.addAll(markerFolder.items)
        mapViewState.refresh()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CTMapView(
            modifier = Modifier.fillMaxSize(),
            mapViewState = mapViewState,
        )

        //        Box(
        //            modifier = Modifier
        //                .wrapContentSize()
        //                .align(Alignment.TopEnd)
        //                .padding(top = CompassTopPadding, end = CTTheme.spacing.large),
        //            contentAlignment = Alignment.Center,
        //        ) {
        //            AnimatedVisibility(
        //                visible = cameraPositionState.position.bearing != 0f,
        //                enter = fadeIn(),
        //                exit = fadeOut(),
        //            ) {
        //                CTCompassButton(
        //                    modifier = Modifier
        //                        .rotate(-cameraPositionState.position.bearing),
        //                    onClick = {
        //                        scope.launch {
        //                            val previousCameraPosition = cameraPositionState.position
        //                            cameraPositionState.animate(
        //                                update = CameraUpdateFactory.newCameraPosition(
        //                                    CameraPosition(previousCameraPosition.target, previousCameraPosition.zoom, 0f, 0f)
        //                                ),
        //                                durationMs = getAnimationDurationFromDegree(cameraPositionState.position.bearing),
        //                            )
        //                        }
        //                    }
        //                )
        //            }
        //        }

        AnimatedVisibility(
            visible = uiState.isLoading,
            modifier = Modifier
                .align(Alignment.BottomCenter),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .animateContentSize()
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(CTTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.large, Alignment.Bottom),
            horizontalAlignment = Alignment.End,
        ) {
            FabIconButton(
                icon = IconSpec.VectorIcon(imageVector = CTTheme.icon.Layer, contentDescription = null),
                onClick = { /*TODO*/ },
                type = FabButtonType.OUTLINED,
            )
            Crossfade(
                targetState = uiState.currentPosition similar mapViewState.mapCenter.toModel()
            ) { isCurrentLocation ->
                if (isCurrentLocation) {
                    FabIconButton(
                        icon = IconSpec.VectorIcon(
                            imageVector = CTTheme.icon.PositionCurrent,
                            contentDescription = null
                        ),
                        onClick = {},
                        type = FabButtonType.COLORED,
                    )
                } else {
                    FabIconButton(
                        icon = IconSpec.VectorIcon(imageVector = CTTheme.icon.Position, contentDescription = null),
                        onClick = {
                            uiState.currentPosition?.let { currentPosition ->
                                scope.launch {
                                    mapViewState.controller.zoomTo(
                                        AppConstants.Map.myLocationZoom,
                                        AppConstants.Map.cameraAnimationDuration.inWholeMilliseconds,
                                    )
                                    mapViewState.controller.animateTo(currentPosition.toGeoPoint())
                                }
                            }
                        },
                        type = FabButtonType.OUTLINED,
                    )
                }
            }
            uiState.cacheSelected?.let { uiCache ->
                CacheBanner(
                    uiCache = uiCache,
                    onClick = navigateToCacheDetail,
                )
            }
        }
    }
}

private fun MapView.setupMap(
    updateMapPosition: (Coordinates) -> Unit,
    unselectCache: () -> Unit,
) {
    // Parameters
    setTileSource(TileSourceFactory.MAPNIK)
    setMultiTouchControls(true)
    zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
    controller.setZoom(AppConstants.Map.defaultZoom)
    controller.setCenter(AppConstants.Map.defaultLocation.toGeoPoint())
    maxZoomLevel = 22.0
    minZoomLevel = 3.5
    isVerticalMapRepetitionEnabled = false
    setScrollableAreaLimitLatitude(82.0, -82.0, 0)

    // Setup my location
    val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), this).apply {
        enableMyLocation()
        enableFollowLocation()
    }
    overlays.add(locationOverlay)

    // Observe map position
    addMapListener(object : MapListener {
        override fun onScroll(event: ScrollEvent?): Boolean {
            updateMapPosition(mapCenter.toModel())
            return true
        }

        override fun onZoom(event: ZoomEvent?): Boolean = true
    })

    // OnTap listener
    val mapEventOverlay = MapEventsOverlay(object : MapEventsReceiver {
        override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
            unselectCache()
            return false
        }

        override fun longPressHelper(p: GeoPoint?): Boolean = false
    })
    overlays.add(mapEventOverlay)
}

private fun MapView.refresh() {
    if (isAnimating) {
        postInvalidate()
    } else {
        invalidate()
    }
}

// private fun getAnimationDurationFromDegree(degree: Float): Int {
//    val degree180 = abs(abs(degree - 180) - 180)
//    val duration = (AppConstants.Map.cameraAnimationDuration.inWholeMilliseconds / 180 * degree180).roundToLong()
//    return maxOf(duration, AppConstants.Map.cameraAnimationDurationMin.inWholeMilliseconds).toInt()
// }
//
// private val CompassTopPadding: Dp = 200.dp

private fun UICache.getCacheMarker(): CacheMarker = when (userStatus) {
    UICache.CacheUserStatus.Owned -> CacheMarker.Owner
    UICache.CacheUserStatus.Found -> CacheMarker.Found
    else -> {
        when (cache) {
            is Cache.Classical -> CacheMarker.Classical
            is Cache.Coop -> CacheMarker.Coop
            is Cache.Mystery -> CacheMarker.Mystery
            is Cache.Piste -> CacheMarker.Piste
        }
    }
}
