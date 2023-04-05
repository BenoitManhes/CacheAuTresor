package com.benoitmanhes.cacheautresor.screen.home.explore

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.cacheautresor.utils.extension.toLatLng
import com.benoitmanhes.cacheautresor.utils.extension.toModel
import com.benoitmanhes.designsystem.molecule.button.CTCompassButton
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToLong

@Composable
internal fun ExploreMapScreen(
    uiState: ExploreUIState,
    cameraPositionState: CameraPositionState,
    updateMapPosition: (Coordinates) -> Unit,
    selectCache: (UICache) -> Unit,
    unselectCache: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.SATELLITE,
                isBuildingEnabled = false,
                isMyLocationEnabled = true,
                maxZoomPreference = 30f,
                minZoomPreference = 5f,
            )
        )
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false,
                mapToolbarEnabled = false,
                myLocationButtonEnabled = false,
                compassEnabled = false,
            )
        )
    }

    LaunchedEffect(cameraPositionState) {
        snapshotFlow { cameraPositionState.position.target to cameraPositionState.isMoving }
            .collect { (position, isMoving) ->
                if (!isMoving) {
                    updateMapPosition(position.toModel())
                }
            }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings,
            onMapClick = { unselectCache() },
        ) {
            uiState.caches.forEach { uiCache ->
                val isSelectedCache = uiCache == uiState.cacheSelected
                Marker(
                    state = MarkerState(position = uiCache.cache.coordinates.toLatLng()),
                    anchor = if (isSelectedCache) Offset(0.5f, 1.0f) else Offset(0.5f, 0.5f),
                    icon = uiCache.getCacheMarker().bitmapDescriptor(context = context, isSelected = isSelectedCache),
                    onClick = {
                        selectCache(uiCache)
                        true
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopEnd)
                .padding(top = CompassTopPadding, end = CTTheme.spacing.large),
            contentAlignment = Alignment.Center,
        ) {
            AnimatedVisibility(
                visible = cameraPositionState.position.bearing != 0f,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                CTCompassButton(
                    modifier = Modifier
                        .rotate(-cameraPositionState.position.bearing),
                    onClick = {
                        scope.launch {
                            val previousCameraPosition = cameraPositionState.position
                            cameraPositionState.animate(
                                update = CameraUpdateFactory.newCameraPosition(
                                    CameraPosition(previousCameraPosition.target, previousCameraPosition.zoom, 0f, 0f)
                                ),
                                durationMs = getAnimationDurationFromDegree(cameraPositionState.position.bearing),
                            )
                        }
                    }
                )
            }
        }

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
                targetState = uiState.currentPosition similar cameraPositionState.position.target.toModel()
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
                            uiState.currentPosition?.toLatLng()?.let {
                                scope.launch {
                                    val previousCameraPosition = cameraPositionState.position
                                    cameraPositionState.animate(
                                        update = CameraUpdateFactory.newCameraPosition(
                                            CameraPosition(it, previousCameraPosition.zoom, 0f, 0f)
                                        ),
                                        durationMs = 1000
                                    )
                                }
                            }
                        },
                        type = FabButtonType.OUTLINED,
                    )
                }
            }
            uiState.cacheSelected?.let { uiCache ->
                CacheBanner(uiCache = uiCache)
            }
        }
    }
}

private fun getAnimationDurationFromDegree(degree: Float): Int {
    val degree180 = abs(abs(degree - 180) - 180)
    val duration = (AppConstants.Map.cameraAnimationDuration.inWholeMilliseconds / 180 * degree180).roundToLong()
    return maxOf(duration, AppConstants.Map.cameraAnimationDurationMin.inWholeMilliseconds).toInt()
}

private val CompassTopPadding: Dp = 200.dp

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
