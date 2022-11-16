package com.benoitmanhes.storage.model.roomModelConverter

import com.benoitmanhes.domain.interfaces.Model
import com.benoitmanhes.storage.model.RoomModel

interface AbstractRoomModelConverter<M : Model, out R : RoomModel<M>> {
    fun buildRoomModel(appModel: M): R
}
