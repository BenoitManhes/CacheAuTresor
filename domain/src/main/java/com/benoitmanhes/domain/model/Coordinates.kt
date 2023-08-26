package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val latitude: Double,
    val longitude: Double,
) : Model
