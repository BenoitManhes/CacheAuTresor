package com.benoitmanhes.cacheautresor.common.uimodel

import android.content.Context
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.screen.home.explore.CacheMarker
import com.benoitmanhes.domain.model.Coordinates
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

data class UIMarker(
    val coordinates: Coordinates,
    val marker: CacheMarker,
    val isSelected: Boolean,
    val onClick: () -> Unit,
) {
    fun getOSMMarker(
        context: Context,
        mapViewState: MapView,
    ): Marker = Marker(mapViewState).apply {
        position = coordinates.toGeoPoint()
        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        icon = marker.getDrawable(
            isSelected = false,
            context = context,
        )
        infoWindow = null
        setOnMarkerClickListener { _, _ ->
            onClick()
            false
        }
    }
}
