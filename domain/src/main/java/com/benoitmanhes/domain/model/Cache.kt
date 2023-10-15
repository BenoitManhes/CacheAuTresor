package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import java.util.Date

sealed interface Cache : Model {

    val cacheId: String
    val creatorId: String
    val title: String
    val coordinates: Coordinates
    val difficulty: Float
    val ground: Float
    val size: CacheSize
    val discovered: Boolean
    val createDate: Date
    val cacheIdsRequired: List<String>
    val tagIds: List<String>
    val finalStepRef: String
    val description: String

    data class Classical(
        override val cacheId: String,
        override val creatorId: String,
        override val title: String,
        override val coordinates: Coordinates,
        override val difficulty: Float,
        override val ground: Float,
        override val size: CacheSize,
        override val discovered: Boolean,
        override val cacheIdsRequired: List<String>,
        override val createDate: Date,
        override val finalStepRef: String,
        override val tagIds: List<String>,
        override val description: String,
    ) : Cache

    data class Piste(
        override val cacheId: String,
        override val creatorId: String,
        override val title: String,
        override val coordinates: Coordinates,
        override val difficulty: Float,
        override val ground: Float,
        override val size: CacheSize,
        override val discovered: Boolean,
        override val cacheIdsRequired: List<String>,
        override val createDate: Date,
        override val finalStepRef: String,
        override val tagIds: List<String>,
        override val description: String,
        val intermediaryStepRefs: List<String>,
    ) : Cache

    data class Mystery(
        override val cacheId: String,
        override val creatorId: String,
        override val title: String,
        override val coordinates: Coordinates,
        override val difficulty: Float,
        override val ground: Float,
        override val size: CacheSize,
        override val discovered: Boolean,
        override val cacheIdsRequired: List<String>,
        override val createDate: Date,
        override val finalStepRef: String,
        override val tagIds: List<String>,
        override val description: String,
        val enigmaStepRef: String,
    ) : Cache

    data class Coop(
        override val cacheId: String,
        override val creatorId: String,
        override val title: String,
        override val coordinates: Coordinates,
        override val difficulty: Float,
        override val ground: Float,
        override val size: CacheSize,
        override val discovered: Boolean,
        override val cacheIdsRequired: List<String>,
        override val createDate: Date,
        override val finalStepRef: String,
        override val tagIds: List<String>,
        override val description: String,
        val crewStepRefs: Map<String, List<String>>,
    ) : Cache
}
