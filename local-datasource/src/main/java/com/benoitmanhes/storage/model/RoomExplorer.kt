package com.benoitmanhes.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benoitmanhes.domain.model.Explorer
import java.util.Date

@Entity(tableName = "explorers")
data class RoomExplorer(
    @PrimaryKey val id: String,
    val name: String,
    val cachesMap: Map<String, Int>,
    val cachesFoundMap: Map<String, Int>,
    val creationDate: Long,
) : RoomModel<Explorer> {

    override fun toAppModel(): Explorer = Explorer(
        explorerId = id,
        name = name,
        cachesMap = cachesMap,
        cachesFoundMap = cachesFoundMap,
        creationDate = Date(creationDate),
    )
}
