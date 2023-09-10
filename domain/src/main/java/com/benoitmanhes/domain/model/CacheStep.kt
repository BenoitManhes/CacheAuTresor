package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import kotlinx.serialization.Serializable

@Serializable
data class CacheStep(
    val stepId: String,
    val instruction: CacheInstructions,
    val clue: String?,
    val validationCode: String,
    val coordinates: Coordinates,
) : Model
