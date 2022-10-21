package com.benoitmanhes.local_datasource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benoitmanhes.domain.model.Explorer

@Entity(tableName = "explorers")
data class RoomExplorer(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo val name: String,
) : RoomModel<Explorer> {

    override fun toAppModel(): Explorer = Explorer(
        explorerId = id,
        name = name,
    )
}
