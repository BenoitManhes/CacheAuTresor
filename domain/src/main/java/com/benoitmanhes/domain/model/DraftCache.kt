package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import kotlinx.serialization.Serializable
import java.util.Date

data class DraftCache(
    val draftCacheId: String,
    val title: String?,
    val coordinates: Coordinates?,
    val difficulty: Float?,
    val ground: Float?,
    val size: CacheSize?,
    val discovered: Boolean?,
    val startCreatingDate: Date?,
    val cacheIdsRequired: List<String>?,
    val tagIds: List<String>?,
    val finalStepRef: String?,
    val description: String?,
    val lockDescription: String?,
    val lockCode: String?,
    val type: Type?,
    val progress: Float,
) : Model {
    sealed interface Type : Model {

        @Serializable
        data object Classical : Type
        data class Piste(
            val intermediateDraftStepIds: List<String>,
        ) : Type

        data class Mystery(
            val enigmaDraftStepId: String?,
        ) : Type

        data class Coop(
            val crewDraftStepsMap: Map<String, List<String>>,
        ) : Type
    }
}
