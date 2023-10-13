package com.benoitmanhes.storage.model.roomModelConverter

import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.storage.model.RoomCacheUserData

object RoomCacheUserDataConverter : AbstractRoomModelConverter<CacheUserData, RoomCacheUserData> {
    override fun buildRoomModel(appModel: CacheUserData): RoomCacheUserData = RoomCacheUserData(
        cacheId = appModel.cacheId,
        note = appModel.note,
        markers = appModel.markers,
    )
}
