package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep

data class UIDraftStepDetail(
    val draftCache: DraftCache,
    val draftStep: DraftCacheStep,
    val type: Type,
) {

    sealed interface Type {

        data object Classical : Type

        data object MysteryEnigma : Type

        data class Piste(
            val index: Int,
        ) : Type

        data class Coop(
            val index: Int,
            val crewRef: String,
        ) : Type

        data object Final : Type
    }
}
