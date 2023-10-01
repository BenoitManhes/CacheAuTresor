package com.benoitmanhes.cacheautresor.common.extensions

import android.location.Location
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.domain.model.Coordinates
import org.osmdroid.api.IGeoPoint
import org.osmdroid.util.GeoPoint

internal fun Location.toModel(): Coordinates = Coordinates(
    latitude = latitude,
    longitude = longitude
)

internal fun Coordinates.toGeoPoint(): GeoPoint = GeoPoint(latitude, longitude)
internal fun IGeoPoint.toModel(): Coordinates = Coordinates(
    latitude = latitude,
    longitude = longitude,
)

internal fun Coordinates.format(format: Coordinates.Format): TextSpec = when (format) {
    Coordinates.Format.DD -> toDDText()
    Coordinates.Format.DM -> toDMText()
    Coordinates.Format.DMS -> toDMSText()
}
private fun Coordinates.toDDText(): TextSpec = TextSpec.Resources(
    R.string.coordinates_DD_format,
    latitude,
    longitude,
)

private fun Coordinates.toDMText(): TextSpec = TextSpec.Resources(
    R.string.coordinates_DM_format,
    latitudeDM.first,
    latitudeDM.second,
    longitudeDM.first,
    longitudeDM.second,
)

private fun Coordinates.toDMSText(): TextSpec = TextSpec.Resources(
    R.string.coordinates_DMS_format,
    latitudeDMS.first,
    latitudeDMS.second,
    latitudeDMS.third,
    longitudeDMS.first,
    longitudeDMS.second,
    longitudeDMS.third,
)
