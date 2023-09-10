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
    val tagIds: List<String>? = null,
    val description: String? = null,
    val intermediaryStepRefs: List<String>? = null,
    val finalStepRef: String? = null,
) : FSCache<Cache.Piste> {

    constructor(cache: Cache.Piste) : this(
        id = cache.cacheId,
        creatorId = cache.creatorId,
        title = cache.title,
        coordinates = cache.coordinates.toFSModel(),
        difficulty = cache.difficulty,
        ground = cache.ground,
        size = cache.size.value,
        discovered = cache.discovered,
        cacheIdsRequired = cache.cacheIdsRequired,
        createDate = Timestamp(cache.createDate),
        tagIds = cache.tagIds,
        description = cache.description,
        intermediaryStepRefs = cache.intermediaryStepRefs,
        finalStepRef = cache.finalStepRef,
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
        tagIds = tagIds ?: emptyList(),
        description = description.requiredField(),
        intermediaryStepRefs = intermediaryStepRefs.requiredField(),
        finalStepRef = finalStepRef.requiredField(),
    )
}
