package com.benoitmanhes.server.extensions

import com.benoitmanhes.domain.model.Coordinates
import com.google.firebase.firestore.GeoPoint

internal fun Coordinates.toFSModel(): GeoPoint = GeoPoint(latitude, longitude)
internal fun GeoPoint.toModel(): Coordinates = Coordinates(
    latitude = latitude,
    longitude = longitude,
)
