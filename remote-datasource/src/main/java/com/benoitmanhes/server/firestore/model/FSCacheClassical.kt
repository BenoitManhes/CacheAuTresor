package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.server.extensions.toFSModel
import com.benoitmanhes.server.extensions.toModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class FSCacheClassical(
    override val id: String? = null,
    val creatorId: String? = null,
    val title: String? = null,
    val coordinates: GeoPoint? = null,
    val difficulty: Float? = null,
    val ground: Float? = null,
    val size: String? = null,
    val discovered: Boolean? = null,
    val createDate: Timestamp? = null,
    val logCode: String? = null,
    val cacheIdsRequired: List<String>? = null,
    val tagIds: List<String>? = null,
    val instructionRef: String? = null,
    val clue: String? = null,
) : FSCache<Cache.Classical> {

    constructor(cache: Cache.Classical) : this(
        creatorId = cache.creatorId,
        title = cache.title,
        coordinates = cache.coordinates.toFSModel(),
        difficulty = cache.difficulty,
        ground = cache.ground,
        size = cache.size.value,
        discovered = cache.discovered,
        createDate = Timestamp(cache.createDate),
        logCode = cache.logCode,
        cacheIdsRequired = cache.cacheIdsRequired,
        tagIds = cache.tagIds,
        clue = cache.clue,
        instructionRef = cache.instructionRef,
    )

    override fun toAppModel(): Cache.Classical = Cache.Classical(
        cacheId = id.requiredField(),
        creatorId = creatorId.requiredField(),
        title = title.requiredField(),
        coordinates = coordinates.requiredField().toModel(),
        difficulty = difficulty.requiredField(),
        ground = ground.requiredField(),
        size = CacheSize.build(size.requiredField()),
        discovered = discovered.requiredField(),
        logCode = logCode.requiredField(),
        createDate = createDate.requiredField().toDate(),
        cacheIdsRequired = cacheIdsRequired ?: emptyList(),
        tagIds = tagIds ?: emptyList(),
        instructionRef = instructionRef.requiredField(),
        clue = clue,
    )
}
