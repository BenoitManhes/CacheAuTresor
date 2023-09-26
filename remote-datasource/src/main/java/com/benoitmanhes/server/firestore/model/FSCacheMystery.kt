package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.server.extensions.toFSModel
import com.benoitmanhes.server.extensions.toModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class FSCacheMystery(
    override val id: String? = null,
    val creatorId: String? = null,
    val title: String? = null,
    val coordinates: GeoPoint? = null,
    val difficulty: Float? = null,
    val ground: Float? = null,
    val size: String? = null,
    val discovered: Boolean? = null,
    val description: String? = null,
    val createDate: Timestamp? = null,
    val tagIds: List<String>? = null,
    val cacheIdsRequired: List<String>? = null,
    val enigmaStepRef: String? = null,
    val finalStepRef: String? = null,

) : FSCache<Cache.Mystery> {

    constructor(cache: Cache.Mystery) : this(
        id = cache.cacheId,
        creatorId = cache.creatorId,
        title = cache.title,
        coordinates = cache.coordinates.toFSModel(),
        difficulty = cache.difficulty,
        ground = cache.ground,
        size = cache.size.value,
        discovered = cache.discovered,
        cacheIdsRequired = cache.cacheIdsRequired,
        tagIds = cache.tagIds,
        createDate = Timestamp(cache.createDate),
        enigmaStepRef = cache.enigmaStepRef,
        finalStepRef = cache.finalStepRef,
        description = cache.description,
    )

    override fun toAppModel(): Cache.Mystery = Cache.Mystery(
        cacheId = id.requiredField(),
        creatorId = creatorId.requiredField(),
        title = title.requiredField(),
        coordinates = coordinates.requiredField().toModel(),
        difficulty = difficulty.requiredField(),
        ground = ground.requiredField(),
        size = CacheSize.build(size.requiredField()),
        discovered = discovered.requiredField(),
        cacheIdsRequired = cacheIdsRequired ?: emptyList(),
        createDate = createDate.requiredField().toDate(),
        tagIds = tagIds ?: emptyList(),
        finalStepRef = finalStepRef.requiredField(),
        enigmaStepRef = enigmaStepRef.requiredField(),
        description = description.requiredField(),
    )
}
