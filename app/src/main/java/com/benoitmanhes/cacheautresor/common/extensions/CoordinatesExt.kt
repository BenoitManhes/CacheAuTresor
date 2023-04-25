package com.benoitmanhes.cacheautresor.common.extensions

import android.location.Location
import com.benoitmanhes.domain.extension.roundCoordinates
import com.benoitmanhes.domain.model.Coordinates
import org.osmdroid.api.IGeoPoint
import org.osmdroid.util.GeoPoint

internal fun Location.toModel(): Coordinates = Coordinates(
    latitude = latitude.roundCoordinates(),
    longitude = longitude.roundCoordinates()
)

internal fun Coordinates.toGeoPoint(): GeoPoint = GeoPoint(latitude, longitude)
internal fun IGeoPoint.toModel(): Coordinates = Coordinates(
    latitude = latitude.roundCoordinates(),
    longitude = longitude.roundCoordinates(),
)
