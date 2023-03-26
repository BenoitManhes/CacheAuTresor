package com.benoitmanhes.cacheautresor.screen.home.explore

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.benoitmanhes.cacheautresor.utils.extension.toLatLng
import com.benoitmanhes.cacheautresor.utils.extension.toModel
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonType
import com.benoitmanhes.designsystem.molecule.button.fabiconbutton.FabIconButton
import com.benoitmanhes.designsystem.res.icons.iconpack.PositionCurrent
import com.benoitmanhes.designsystem.res.icons.iconpack.Layer
import com.benoitmanhes.designsystem.res.icons.iconpack.Position
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

@Composable
internal fun ExploreMapScreen(
    uiState: ExploreUIState,
    cameraPositionState: CameraPositionState,
    updateMapPosition: (Coordinates) -> Unit,
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

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = mapUiSettings,
    ) {
        uiState.caches.forEach { uiCache ->
            Marker(
                state = MarkerState(position = uiCache.cache.coordinates.toLatLng()),
                icon = uiCache.getCacheMarker().bitmapDescriptor(context),
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                    icon = IconSpec.VectorIcon(imageVector = CTTheme.icon.PositionCurrent, contentDescription = null),
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
    }
}

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
