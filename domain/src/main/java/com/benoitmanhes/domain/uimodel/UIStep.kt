package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.CacheInstructions
import com.benoitmanhes.domain.model.Coordinates

data class UIStep(
    val clue: String?,
    val showClue: Boolean,
    val instructions: CacheInstructions,
    val status: Status,
    val coordinates: Coordinates,
) {
    enum class Status {
        Done, Current, Lock
    }
}
