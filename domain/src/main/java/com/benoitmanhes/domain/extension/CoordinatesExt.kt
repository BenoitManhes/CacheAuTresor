package com.benoitmanhes.domain.extension

import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.utils.DomainConstants
import kotlin.math.abs

infix fun Coordinates?.similar(other: Coordinates?): Boolean =
    if (this != null && other != null) {
        abs(latitude - other.latitude) <= DomainConstants.Location.coordinatesApproximateError
            && abs(longitude - other.longitude) <= DomainConstants.Location.coordinatesApproximateError
    } else {
        this == other
    }
