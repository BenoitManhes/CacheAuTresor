package com.benoitmanhes.cacheautresor.common.uimodel

import com.benoitmanhes.cacheautresor.common.maps.CacheMarker
import com.benoitmanhes.cacheautresor.common.maps.CacheMarkerIcon
import com.benoitmanhes.domain.model.Coordinates
import org.osmdroid.views.MapView

data class UIMarker(
    val coordinates: Coordinates,
    val iconMarker: CacheMarkerIcon,
    val isSelected: Boolean,
    val onClick: () -> Unit = {},
) {
    fun getOSMMarker(
        mapViewState: MapView,
    ): CacheMarker = CacheMarker(
        mapView = mapViewState,
        cacheIcon = iconMarker,
        coordinates = coordinates,
        isSelected = isSelected,
        onClick = onClick,
    )
}
