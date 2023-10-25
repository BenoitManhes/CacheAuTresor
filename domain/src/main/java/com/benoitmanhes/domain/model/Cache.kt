package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import kotlinx.serialization.Serializable
import java.util.Date

data class Cache(
    val cacheId: String,
    val creatorId: String,
    val title: String,
    val coordinates: Coordinates,
    val difficulty: Float,
    val ground: Float,
    val size: CacheSize,
    val discovered: Boolean,
    val createDate: Date,
    val cacheIdsRequired: List<String>,
    val tagIds: List<String>,
    val finalStepRef: String,
    val description: String,
    val type: Type,
) : Model {

    sealed interface Type : Model {

        @Serializable
        object Classical : Type
        data class Piste(
            val intermediateStepIds: List<String>,
        ) : Type

        data class Mystery(
            val enigmaStepId: String,
        ) : Type

        data class Coop(
            val crewStepsMap: Map<String, List<String>>,
        ) : Type
    }
}
