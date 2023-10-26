package com.benoitmanhes.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benoitmanhes.domain.model.Explorer

@Entity(tableName = "explorers")
data class RoomExplorer(
    @PrimaryKey val id: String,
    val name: String,
    val cachesMap: Map<String, Int>,
    val explorationPts: Int,
) : RoomModel<Explorer> {

    override fun toAppModel(): Explorer = Explorer(
        explorerId = id,
        name = name,
        cachesMap = cachesMap,
        explorationPts = explorationPts,
    )
}
