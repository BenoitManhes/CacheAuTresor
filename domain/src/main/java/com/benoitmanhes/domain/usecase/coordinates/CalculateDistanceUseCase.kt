package com.benoitmanhes.domain.usecase.coordinates

import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.model.Distance
import com.benoitmanhes.domain.model.Distance.Companion.meters
import javax.inject.Inject
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class CalculateDistanceUseCase @Inject constructor() {
    operator fun invoke(coordinates1: Coordinates, coordinates2: Coordinates): Distance =
        haversine(
            lat1 = coordinates1.latitude,
            lon1 = coordinates1.longitude,
            lat2 = coordinates2.latitude,
            lon2 = coordinates2.longitude,
        ).meters

    fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371e3 // Earth radius in meters
        val phi1 = Math.toRadians(lat1)
        val phi2 = Math.toRadians(lat2)
        val deltaPhi = Math.toRadians(lat2 - lat1)
        val deltaLambda = Math.toRadians(lon2 - lon1)

        val a = sin(deltaPhi / 2) * sin(deltaPhi / 2) +
            cos(phi1) * cos(phi2) *
            sin(deltaLambda / 2) * sin(deltaLambda / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return R * c
    }
}
