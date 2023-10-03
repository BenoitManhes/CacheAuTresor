package com.benoitmanhes.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benoitmanhes.domain.model.CacheUserData

@Entity(tableName = "cache-user-data")
data class RoomCacheUserData(
    @PrimaryKey val cacheId: String,
    val isStarted: Boolean,
    val note: String? = null,
    val markers: List<CacheUserData.Marker> = emptyList(),
) : RoomModel<CacheUserData> {

    override fun toAppModel(): CacheUserData = CacheUserData(
        cacheId = cacheId,
        isStarted = isStarted,
        note = note,
        markers = markers,
    )
}
