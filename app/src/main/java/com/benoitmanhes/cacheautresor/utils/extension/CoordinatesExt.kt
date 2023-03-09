package com.benoitmanhes.cacheautresor.utils.extension

import android.location.Location
import com.benoitmanhes.domain.extension.roundCoordinates
import com.benoitmanhes.domain.extension.roundDecimal
import com.benoitmanhes.domain.model.Coordinates
import com.google.android.gms.maps.model.LatLng

internal fun Coordinates.toLatLng(): LatLng = LatLng(latitude, longitude)
internal fun LatLng.toModel(): Coordinates = Coordinates(latitude = latitude.roundCoordinates(), longitude = longitude.roundCoordinates())
internal fun Location.toModel(): Coordinates = Coordinates(latitude = latitude.roundCoordinates(), longitude = longitude.roundCoordinates())