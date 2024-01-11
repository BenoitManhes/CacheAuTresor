package com.benoitmanhes.storage.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.model.DraftCache
import java.util.Date

@Entity(tableName = "draft-cache")
data class RoomDraftCache(
    @PrimaryKey val draftCacheId: String,
    val title: String?,
    @Embedded(prefix = "coordinates_") val coordinates: Coordinates?,
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
    val type: DraftCache.Type?,
) : RoomModel<DraftCache> {

    override fun toAppModel(): DraftCache = DraftCache(
        draftCacheId = draftCacheId,
        title = title,
        coordinates = coordinates,
        difficulty = difficulty,
        ground = ground,
        size = size,
        discovered = discovered,
        startCreatingDate = startCreatingDate,
        cacheIdsRequired = cacheIdsRequired,
        tagIds = tagIds,
        finalStepRef = finalStepRef,
        description = description,
        lockDescription = lockDescription,
        lockCode = lockCode,
        type = type,
    )

    companion object {
        fun build(appModel: DraftCache): RoomDraftCache = RoomDraftCache(
            draftCacheId = appModel.draftCacheId,
            title = appModel.title,
            coordinates = appModel.coordinates,
            difficulty = appModel.difficulty,
            ground = appModel.ground,
            size = appModel.size,
            discovered = appModel.discovered,
            startCreatingDate = appModel.startCreatingDate,
            cacheIdsRequired = appModel.cacheIdsRequired,
            tagIds = appModel.tagIds,
            finalStepRef = appModel.finalStepRef,
            description = appModel.description,
            lockDescription = appModel.lockDescription,
            lockCode = appModel.lockCode,
            type = appModel.type,
        )
    }
}
