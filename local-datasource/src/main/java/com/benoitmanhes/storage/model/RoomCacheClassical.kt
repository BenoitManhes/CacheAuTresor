package com.benoitmanhes.storage.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.domain.model.Coordinates
import java.util.Date

@Entity(tableName = "classical_caches")
data class RoomCacheClassical(
    @PrimaryKey @ColumnInfo(name = "id") val cacheId: String,
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
    val instructionRef: String,
    val clue: String?,
) : RoomModel<Cache.Classical> {

    override fun toAppModel(): Cache.Classical = Cache.Classical(
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
        instructionRef = instructionRef,
        clue = clue,
    )
}
