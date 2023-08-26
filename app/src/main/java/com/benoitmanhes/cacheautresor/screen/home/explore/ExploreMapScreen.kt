package com.benoitmanhes.cacheautresor.screen.home.explore

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.benoitmanhes.cacheautresor.common.CTMapView
import com.benoitmanhes.cacheautresor.common.extensions.getOSMMarker
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.extensions.toModel
import com.benoitmanhes.cacheautresor.common.rememberMapViewWithLifecycle
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonType
import com.benoitmanhes.designsystem.molecule.button.fabiconbutton.FabIconButton
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.Layer
import com.benoitmanhes.designsystem.res.icons.iconpack.Position
import com.benoitmanhes.designsystem.res.icons.iconpack.PositionCurrent
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.extensions.getPrimaryColor
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
    navigateToCacheDetail: (String) -> Unit,
) {
    val mapViewState = rememberMapViewWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var lastCacheSelected: UIExploreCache? by remember {
        mutableStateOf(null)
    }
    lastCacheSelected = remember(uiState.cacheSelected) {
        uiState.cacheSelected ?: lastCacheSelected
    }

    val markerFolder = remember { FolderOverlay() }

    LaunchedEffect(mapViewState) {
        mapViewState.setupMap(unselectCache = unselectCache, updateMapPosition = updateMapPosition)
    }

    LaunchedEffect(uiState.caches, uiState.cacheSelected) {
        mapViewState.overlays.removeAll(markerFolder.items)
        markerFolder.items.clear()

        uiState.caches.sortedBy { it == uiState.cacheSelected }.forEach { uiCache ->
            val marker = uiCache.getOSMMarker(
                context = context,
                mapViewState = mapViewState,
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
            Box(Modifier.wrapContentHeight()) {
                Box(modifier = Modifier.height(height = Dimens.Size.cacheCardHeight)) {}

                this@Column.AnimatedVisibility(
                    visible = uiState.cacheSelected != null,
                    enter = slideInVertically(
                        animationSpec = tween(),
                        initialOffsetY = { (it * 1.5).toInt() },
                    ),
                    exit = slideOutVertically(
                        animationSpec = tween(),
                        targetOffsetY = { (it * 1.5).toInt() },
                    )
                ) {
                    (uiState.cacheSelected ?: lastCacheSelected)?.let { uiCache ->
                        val cacheBannerColor by animateColorAsState(uiCache.getPrimaryColor())
                        CacheBanner(
                            uiExploreCache = uiCache,
                            onClick = { navigateToCacheDetail(uiCache.cache.cacheId) },
                            color = cacheBannerColor,
                        )
                    }
                }
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
