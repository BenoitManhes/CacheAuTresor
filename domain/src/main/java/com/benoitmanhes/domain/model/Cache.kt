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
    val logCode: String
    val cacheIdsRequired: List<String>
    val tagIds: List<String>

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
        override val logCode: String,
        override val tagIds: List<String>,
        val instructionRef: String,
        val clue: String?,
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
        override val logCode: String,
        override val tagIds: List<String>,
        val description: String,
        val steps: List<CacheStep>,
        val finalCoordinates: Coordinates,
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
        override val logCode: String,
        override val tagIds: List<String>,
        val instructionRef: String,
        val clue: String?,
        val finalCoordinates: Coordinates,
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
        override val logCode: String,
        override val tagIds: List<String>,
        val description: String,
        val instructionRefs: List<String>,
        val finalCoordinates: Coordinates,
    ) : Cache
}
