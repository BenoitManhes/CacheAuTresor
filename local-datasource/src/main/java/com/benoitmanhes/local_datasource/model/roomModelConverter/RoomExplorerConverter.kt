package com.benoitmanhes.local_datasource.model.roomModelConverter

import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.local_datasource.model.RoomExplorer

object RoomExplorerConverter : AbstractRoomModelConverter<Explorer, RoomExplorer>() {
    override fun buildRoomModel(appModel: Explorer): RoomExplorer = RoomExplorer(
        id = appModel.explorerId,
        name = appModel.name,
    )
}
