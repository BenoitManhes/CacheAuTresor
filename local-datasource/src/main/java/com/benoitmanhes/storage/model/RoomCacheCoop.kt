package com.benoitmanhes.storage.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.domain.model.Coordinates
import java.util.Date

@Entity(tableName = "caches_coop")
data class RoomCacheCoop(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val cacheId: String,
    val creatorId: String,
    val title: String,
    @Embedded val coordinates: Coordinates,
    val difficulty: Float,
    val ground: Float,
    val size: CacheSize,
    val discovered: Boolean,
    val cacheIdsRequired: List<String>,
    val createDate: Date,
    val logCode: String,
    val tagIds: List<String>,
    val description: String,
    val instructionRefs: List<String>,
    @Embedded val finalCoordinates: Coordinates,
) : RoomModel<Cache.Coop> {

    override fun toAppModel(): Cache.Coop = Cache.Coop(
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
        logCode = logCode,
        tagIds = tagIds,
        description = description,
        instructionRefs = instructionRefs,
        finalCoordinates = finalCoordinates,
    )
}
