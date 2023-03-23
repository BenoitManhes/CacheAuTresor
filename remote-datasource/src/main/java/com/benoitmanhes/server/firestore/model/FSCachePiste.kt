package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.server.extensions.toFSModel
import com.benoitmanhes.server.extensions.toModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class FSCachePiste(
    override val id: String? = null,
    val creatorId: String? = null,
    val title: String? = null,
    val coordinates: GeoPoint? = null,
    val difficulty: Float? = null,
    val ground: Float? = null,
    val size: String? = null,
    val discovered: Boolean? = null,
    val cacheIdsRequired: List<String>? = null,
    val createDate: Timestamp? = null,
    val logCode: String? = null,
    val tagIds: List<String>? = null,
    val description: String? = null,
    val stepRefs: List<String>? = null,
    val finalCoordinates: GeoPoint? = null,
) : FSCache<Cache.Piste> {

    constructor(cache: Cache.Piste) : this(
        creatorId = cache.creatorId,
        title = cache.title,
        coordinates = cache.coordinates.toFSModel(),
        difficulty = cache.difficulty,
        ground = cache.ground,
        size = cache.size.value,
        discovered = cache.discovered,
        cacheIdsRequired = cache.cacheIdsRequired,
        createDate = Timestamp(cache.createDate),
        logCode = cache.logCode,
        tagIds = cache.tagIds,
        description = cache.description,
        stepRefs = cache.steps.map { it.stepId },
        finalCoordinates = cache.finalCoordinates.toFSModel(),
    )

    override fun toAppModel(): Cache.Piste = Cache.Piste(
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
        logCode = logCode.requiredField(),
        tagIds = tagIds ?: emptyList(),
        description = description.requiredField(),
        steps = emptyList(),
        finalCoordinates = finalCoordinates.requiredField().toModel(),
    )
}
