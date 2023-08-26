package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import kotlinx.serialization.Serializable

@Serializable
data class CacheStep(
    val stepId: String,
    val instructionRef: String,
    val clue: String?,
    val validateCode: String?,
) : Model
