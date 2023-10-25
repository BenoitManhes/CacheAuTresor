package com.benoitmanhes.storage.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.domain.model.Coordinates
import java.util.Date

@Entity(tableName = "caches")
data class RoomCache(
    @PrimaryKey val cacheId: String,
    val creatorId: String,
    val title: String,
    @Embedded(prefix = "coordinates_") val coordinates: Coordinates,
    val difficulty: Float,
    val ground: Float,
    val size: CacheSize,
    val discovered: Boolean,
    val createDate: Date,
    val cacheIdsRequired: List<String>,
    val tagIds: List<String>,
    val description: String,
    val finalStepRef: String,
    val type: Cache.Type,
) : RoomModel<Cache> {

    override fun toAppModel(): Cache = Cache(
        cacheId = cacheId,
        creatorId = creatorId,
        title = title,
        coordinates = coordinates,
        difficulty = difficulty,
        ground = ground,
        size = size,
        discovered = discovered,
        cacheIdsRequired = cacheIdsRequired,
        createDate = createDate,
        tagIds = tagIds,
        finalStepRef = finalStepRef,
        description = description,
        type = type,
    )

    companion object {
        fun build(appModel: Cache): RoomCache = RoomCache(
            cacheId = appModel.cacheId,
            creatorId = appModel.creatorId,
            title = appModel.title,
            coordinates = appModel.coordinates,
            difficulty = appModel.difficulty,
            ground = appModel.ground,
            size = appModel.size,
            discovered = appModel.discovered,
            createDate = appModel.createDate,
            cacheIdsRequired = appModel.cacheIdsRequired,
            tagIds = appModel.tagIds,
            description = appModel.description,
            finalStepRef = appModel.finalStepRef,
            type = appModel.type,
        )
    }
}
