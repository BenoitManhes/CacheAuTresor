package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import kotlinx.serialization.Serializable

data class CacheUserData(
    val cacheId: String,
    val isStarted: Boolean = false,
    val note: String? = null,
    val markers: List<Marker> = emptyList(),
) : Model {

    @Serializable
    data class Marker(
        val coordinates: Coordinates,
        val title: String,
    ) : Model
}
