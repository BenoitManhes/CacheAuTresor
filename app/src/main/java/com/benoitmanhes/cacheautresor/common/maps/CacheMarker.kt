package com.benoitmanhes.cacheautresor.common.maps

import android.graphics.Rect
import android.view.MotionEvent
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.screen.home.explore.CacheMarkerIcon
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.domain.model.Coordinates
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class CacheMarker(
    mapView: MapView,
    cacheIcon: CacheMarkerIcon,
    coordinates: Coordinates,
    val isSelected: Boolean,
    onClick: (() -> Unit)? = null,
) : Marker(mapView) {

    init {
        position = coordinates.toGeoPoint()
        setAnchor(
            ANCHOR_CENTER,
            if (isSelected) ANCHOR_BOTTOM else ANCHOR_CENTER,
        )
        icon = cacheIcon.getDrawable(
            isSelected = isSelected,
            context = mapView.context,
        )
        onClick?.let {
            setOnMarkerClickListener { _, _ ->
                onClick()
                true
            }
        }
    }

    override fun hitTest(event: MotionEvent, mapView: MapView): Boolean {
        // Increase the clickable zone by defining a larger bounding box
        val boundingBox = Rect()
        val icon = icon

        if (icon != null && !isSelected) {
            val markerPosition = mapView.projection.toPixels(position, null)

            val anchorOffsetX = icon.intrinsicWidth / 2
            val anchorOffsetY = icon.intrinsicHeight

            boundingBox.set(
                markerPosition.x - anchorOffsetX - AppConstants.Map.markerTouchMargin,
                markerPosition.y - anchorOffsetY - AppConstants.Map.markerTouchMargin,
                markerPosition.x + anchorOffsetX + AppConstants.Map.markerTouchMargin,
                markerPosition.y + anchorOffsetY + AppConstants.Map.markerTouchMargin
            )

            // Check if the touch event is within the enlarged bounding box
            return boundingBox.contains(event.x.toInt(), event.y.toInt())
        }

        // Use the default hitTest implementation if no icon is set
        return super.hitTest(event, mapView)
    }
}
