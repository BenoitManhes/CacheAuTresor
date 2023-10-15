package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.server.extensions.toFSModel
import com.benoitmanhes.server.extensions.toModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class FSCacheCoop(
    override val id: String? = null,
    val creatorId: String? = null,
    val title: String? = null,
    val coordinates: GeoPoint? = null,
    val difficulty: Float? = null,
    val ground: Float? = null,
    val size: String? = null,
    val discovered: Boolean? = null,
    val logCode: String? = null,
    val description: String? = null,
    val createDate: Timestamp? = null,
    val tagIds: List<String>? = null,
    val cacheIdsRequired: List<String>? = null,
    val finalStepRef: String? = null,
    val crewStepRefs: Map<String, List<String>>? = null,
) : FSCache<Cache.Coop> {

    constructor(cache: Cache.Coop) : this(
        id = cache.cacheId,
        creatorId = cache.creatorId,
        title = cache.title,
        coordinates = cache.coordinates.toFSModel(),
        difficulty = cache.difficulty,
        ground = cache.ground,
        size = cache.size.value,
        discovered = cache.discovered,
        cacheIdsRequired = cache.cacheIdsRequired,
        description = cache.description,
        tagIds = cache.tagIds,
        createDate = Timestamp(cache.createDate),
        finalStepRef = cache.finalStepRef,
        crewStepRefs = cache.crewStepRefs,
    )

    override fun toAppModel(): Cache.Coop = Cache.Coop(
        cacheId = id.requiredField(),
        creatorId = creatorId.requiredField(),
        title = title.requiredField(),
        coordinates = coordinates.requiredField().toModel(),
        difficulty = difficulty.requiredField(),
        ground = ground.requiredField(),
        size = CacheSize.build(size.requiredField()),
        discovered = discovered.requiredField(),
        createDate = createDate.requiredField().toDate(),
        tagIds = tagIds ?: emptyList(),
        cacheIdsRequired = cacheIdsRequired ?: emptyList(),
        description = description.requiredField(),
        finalStepRef = finalStepRef.requiredField(),
        crewStepRefs = crewStepRefs.requiredField(),
    )
}
