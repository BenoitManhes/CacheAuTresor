package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.CacheInstructions
import com.benoitmanhes.domain.model.Coordinates

data class UIStep(
    val stepId: String,
    val clue: String?,
    val showClue: Boolean,
    val instructions: CacheInstructions,
    val status: Status,
    val coordinates: Coordinates,
    val type: Type,
    val code: String,
) {

    sealed interface Type {

        data object Classical : Type

        data object Mystery : Type

        data class Piste(
            val index: Int,
        ) : Type

        data class Coop(
            val index: Int,
            val crewPosition: String,
        ) : Type

        data object Final : Type
    }

    enum class Status {
        Done, Current, Lock
    }
}
