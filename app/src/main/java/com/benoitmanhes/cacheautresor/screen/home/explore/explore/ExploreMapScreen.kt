package com.benoitmanhes.cacheautresor.screen.home.explore.explore

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.benoitmanhes.cacheautresor.BuildConfig
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.extensions.getCacheMarker
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.extensions.toModel
import com.benoitmanhes.cacheautresor.common.maps.CTMapView
import com.benoitmanhes.cacheautresor.common.maps.CacheMarker
import com.benoitmanhes.cacheautresor.common.maps.rememberMapViewWithLifecycle
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonType
import com.benoitmanhes.designsystem.molecule.button.fabiconbutton.FabIconButton
import com.benoitmanhes.designsystem.res.icons.iconpack.Layer
import com.benoitmanhes.designsystem.res.icons.iconpack.Position
import com.benoitmanhes.designsystem.res.icons.iconpack.PositionCurrent
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.AnimatedNullableVisibility
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.domain.extension.similar
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.uimodel.UIExploreCache
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
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@Composable
internal fun ExploreMapScreen(
    uiState: ExploreUIState,
    updateMapPosition: (Coordinates) -> Unit,
    selectCache: (UIExploreCache) -> Unit,
    unselectCache: () -> Unit,
    requestLocationPermission: () -> Unit,
) {
    val mapViewState = rememberMapViewWithLifecycle()
    val scope = rememberCoroutineScope()
    val markerFolder = remember { FolderOverlay() }
    val context = LocalContext.current

    LaunchedEffect(mapViewState) {
        mapViewState.setupMap(unselectCache = unselectCache, updateMapPosition = updateMapPosition)
    }

    LaunchedEffect(uiState.caches, uiState.cacheSelected) {
        mapViewState.overlays.removeAll(markerFolder.items)
        markerFolder.items.clear()

        uiState.caches.sortedBy { it == uiState.cacheSelected }.forEach { uiCache ->
            val marker = CacheMarker(
                mapView = mapViewState,
                cacheIcon = uiCache.cache.getCacheMarker(uiCache.userStatus),
                coordinates = uiCache.cache.coordinates,
                isSelected = uiState.cacheSelected == uiCache,
                onClick = { selectCache(uiCache) },
            )
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
                .fillMaxWidth()
                .wrapContentHeight()
                .animateContentSize()
                .padding(CTTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.large, Alignment.Bottom),
            horizontalAlignment = Alignment.End,
        ) {
            if (BuildConfig.DEBUG) {
                FabIconButton(
                    icon = IconSpec.VectorIcon(imageVector = CTTheme.icon.Layer, contentDescription = null),
                    onClick = { /*TODO*/ },
                    type = FabButtonType.OUTLINED,
                )
            }
            Crossfade(
                targetState = uiState.currentPosition similar mapViewState.mapCenter.toModel(),
                label = "crossfade-position"
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
                            val hasLocationPermission = ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                            ) == PackageManager.PERMISSION_GRANTED
                            if (!hasLocationPermission) {
                                requestLocationPermission()
                            } else {
                                uiState.currentPosition?.let { currentPosition ->
                                    scope.launch {
                                        mapViewState.controller.zoomTo(
                                            AppConstants.Map.myLocationZoom,
                                            AppConstants.Map.cameraAnimationDuration.inWholeMilliseconds,
                                        )
                                        mapViewState.controller.animateTo(currentPosition.toGeoPoint())
                                    }
                                }
                            }
                        },
                        type = FabButtonType.OUTLINED,
                    )
                }
            }
            AnimatedNullableVisibility(
                value = uiState.cacheBanner,
                enter = slideInVertically(
                    animationSpec = tween(),
                    initialOffsetY = { (it * 1.5).toInt() },
                ),
                exit = slideOutVertically(
                    animationSpec = tween(),
                    targetOffsetY = { (it * 1.5).toInt() },
                )
            ) { value ->
                value.Content()
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
    maxZoomLevel = AppConstants.Map.maxZoom
    minZoomLevel = AppConstants.Map.minZoom
    isVerticalMapRepetitionEnabled = false
    setScrollableAreaLimitLatitude(AppConstants.Map.areaLimitLatNorth, AppConstants.Map.areaLimitLatSouth, 0)

    // Setup my location
    val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), this).apply {
        enableMyLocation()
        enableFollowLocation()
        setPersonIcon(
            ContextCompat.getDrawable(context, R.drawable.marker_me)?.toBitmap()
        )
        setPersonAnchor(0.5f, 0.5f)
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

fun MapView.refresh() {
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
