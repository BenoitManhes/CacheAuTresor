package com.benoitmanhes.domain.usecase.common

import com.benoitmanhes.domain.model.Coordinates
import javax.inject.Inject
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class CalculateDistanceUseCase @Inject constructor() {
    operator fun invoke(coordinates1: Coordinates, coordinates2: Coordinates): Double =
        distance(
            lat1 = coordinates1.latitude,
            lon1 = coordinates1.longitude,
            lat2 = coordinates2.latitude,
            lon2 = coordinates2.longitude,
        )

    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist = (
            sin(deg2rad(lat1))
                * sin(deg2rad(lat2))
                + cos(deg2rad(lat1))
                    * cos(deg2rad(lat2))
                    * cos(deg2rad(theta))
            )
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515 * 1000
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}
