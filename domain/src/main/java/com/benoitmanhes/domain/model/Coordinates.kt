package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model

data class Coordinates(
    val latitude: Double,
    val longitude: Double,
) : Model
