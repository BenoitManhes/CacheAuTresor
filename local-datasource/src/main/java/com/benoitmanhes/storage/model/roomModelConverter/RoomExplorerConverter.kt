package com.benoitmanhes.storage.model.roomModelConverter

import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.storage.model.RoomExplorer

object RoomExplorerConverter : AbstractRoomModelConverter<Explorer, RoomExplorer> {
    override fun buildRoomModel(appModel: Explorer): RoomExplorer = RoomExplorer(
        id = appModel.explorerId,
        name = appModel.name,
        cacheIdsFound = appModel.cacheIdsFound,
    )
}
