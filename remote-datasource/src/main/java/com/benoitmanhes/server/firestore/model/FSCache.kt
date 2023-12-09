package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.server.extensions.toFSModel
import com.benoitmanhes.server.extensions.toModel
import com.benoitmanhes.server.firestore.builder.CacheTypeBuilder
import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class FSCache(
    override val id: String? = null,
    val creatorId: String? = null,
    val title: String? = null,
    val coordinates: GeoPoint? = null,
    val difficulty: Float? = null,
    val ground: Float? = null,
    val size: String? = null,
    val discovered: Boolean? = null,
    val createDate: Timestamp? = null,
    val cacheIdsRequired: List<String>? = null,
    val tagIds: List<String>? = null,
    val finalStepRef: String? = null,
    val description: String? = null,
    val lockDescription: String? = null,
    val lockCode: String? = null,
    val type: Map<String, Any>? = null,
) : FirestoreModel<Cache> {

    constructor(cache: Cache) : this(
        id = cache.cacheId,
        creatorId = cache.creatorId,
        title = cache.title,
        coordinates = cache.coordinates.toFSModel(),
        difficulty = cache.difficulty,
        ground = cache.ground,
        size = cache.size.value,
        discovered = cache.discovered,
        createDate = Timestamp(cache.createDate),
        cacheIdsRequired = cache.cacheIdsRequired,
        tagIds = cache.tagIds,
        finalStepRef = cache.finalStepRef,
        description = cache.description,
        lockDescription = cache.lockDescription,
        lockCode = cache.lockCode,
        type = CacheTypeBuilder.fsModel(cache.type),
    )

    override fun toAppModel(): Cache = Cache(
        cacheId = id.requiredField(),
        creatorId = creatorId.requiredField(),
        title = title.requiredField(),
        coordinates = coordinates.requiredField().toModel(),
        difficulty = difficulty.requiredField(),
        ground = ground.requiredField(),
        size = CacheSize.build(size.requiredField()),
        discovered = discovered.requiredField(),
        createDate = createDate.requiredField().toDate(),
        cacheIdsRequired = cacheIdsRequired ?: emptyList(),
        tagIds = tagIds ?: emptyList(),
        finalStepRef = finalStepRef.requiredField(),
        description = description.requiredField(),
        lockDescription = lockDescription.requiredField(),
        lockCode = lockCode.requiredField(),
        type = CacheTypeBuilder.appModel(type.requiredField()),
    )
}
