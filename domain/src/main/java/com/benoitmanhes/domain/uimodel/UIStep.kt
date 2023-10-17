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
) {

    sealed interface Type {
        val isFinal: Boolean

        object Classical : Type {
            override val isFinal: Boolean = true
        }

        data class Mystery(
            override val isFinal: Boolean,
        ) : Type

        data class Piste(
            override val isFinal: Boolean,
            val index: Int,
        ) : Type

        data class Coop(
            override val isFinal: Boolean,
            val index: Int,
            val crewPosition: String,
        ) : Type
    }

    enum class Status {
        Done, Current, Lock
    }
}
