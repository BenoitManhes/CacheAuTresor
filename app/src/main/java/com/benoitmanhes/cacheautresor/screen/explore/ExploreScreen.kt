package com.benoitmanhes.cacheautresor.screen.explore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(45.75, 4.85), 15f)
    }
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.HYBRID,
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
            )
        )
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings,
        )
    }
}
