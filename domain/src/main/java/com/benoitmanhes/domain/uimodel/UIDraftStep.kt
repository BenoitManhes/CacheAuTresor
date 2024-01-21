package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Coordinates

data class UIDraftStep(
    val stepDraftId: String,
    val type: Type,
    val instructions: String?,
    val clue: String?,
    val validationCode: String?,
    val coordinates: Coordinates?,
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
}
