package com.benoitmanhes.cacheautresor.common.extensions

import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.domain.model.Coordinates
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

internal fun MapView.setUpDefaultParameters(
    center: Coordinates = AppConstants.Map.defaultLocation,
) {
    setTileSource(TileSourceFactory.MAPNIK)
    setMultiTouchControls(true)
    zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
    controller.setZoom(AppConstants.Map.defaultZoom)
    controller.setCenter(center.toGeoPoint())
    maxZoomLevel = AppConstants.Map.maxZoom
    minZoomLevel = AppConstants.Map.minZoom
    isVerticalMapRepetitionEnabled = false
    setScrollableAreaLimitLatitude(AppConstants.Map.areaLimitLatNorth, AppConstants.Map.areaLimitLatSouth, 0)
}

internal fun MapView.setUpMyLocation() {
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
}

internal fun MapView.observeMapPosition(
    updateMapPosition: (Coordinates) -> Unit,
) {
    // Observe map position
    addMapListener(object : MapListener {
        override fun onScroll(event: ScrollEvent?): Boolean {
            updateMapPosition(mapCenter.toModel())
            return true
        }

        override fun onZoom(event: ZoomEvent?): Boolean = true
    })
}

internal fun MapView.onTapListener(
    onSingleTap: () -> Boolean = { false },
    onLongPress: (Coordinates?) -> Boolean = { false },
) {
    val mapEventOverlay = MapEventsOverlay(object : MapEventsReceiver {
        override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean = onSingleTap()
        override fun longPressHelper(p: GeoPoint?): Boolean = onLongPress(p?.toModel())
    })
    overlays.add(mapEventOverlay)
}

internal fun MapView.refresh() {
    if (isAnimating) {
        postInvalidate()
    } else {
        invalidate()
    }
}
