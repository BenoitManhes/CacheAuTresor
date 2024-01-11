package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model

data class DraftCacheStep(
    val stepDraftId: String,
    val instruction: String?,
    val clue: String?,
    val validationCode: String?,
    val coordinates: Coordinates?,
) : Model
