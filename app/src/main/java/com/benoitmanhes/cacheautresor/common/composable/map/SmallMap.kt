package com.benoitmanhes.cacheautresor.common.composable.map

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.benoitmanhes.cacheautresor.common.extensions.refresh
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.maps.CTMapView
import com.benoitmanhes.cacheautresor.common.maps.rememberMapViewWithLifecycle
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.designsystem.theme.CTTheme
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.overlay.FolderOverlay

@Composable
fun SmallMap(
    uiMarker: UIMarker,
) {
    val mapViewState = rememberMapViewWithLifecycle()
    val markerFolder = remember { FolderOverlay() }

    LaunchedEffect(mapViewState) {
        mapViewState.apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(false)
            zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
            controller.setZoom(AppConstants.Map.smallMapZoom)
            isVerticalMapRepetitionEnabled = false
        }
    }

    LaunchedEffect(uiMarker) {
        mapViewState.overlays.removeAll(markerFolder.items)
        markerFolder.items.clear()
        markerFolder.add(uiMarker.getOSMMarker(mapViewState))
        mapViewState.overlays.addAll(markerFolder.items)
        mapViewState.controller.setCenter(uiMarker.coordinates.toGeoPoint())
        mapViewState.refresh()
    }

    CTMapView(
        modifier = Modifier
            .size(AppDimens.Map.smallMapSize)
            .clip(CTTheme.shape.small),
        mapViewState = mapViewState,
    )
}
