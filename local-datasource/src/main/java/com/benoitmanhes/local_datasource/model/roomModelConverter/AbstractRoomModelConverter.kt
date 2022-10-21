package com.benoitmanhes.local_datasource.model.roomModelConverter

import com.benoitmanhes.domain.interfaces.Model
import com.benoitmanhes.local_datasource.model.RoomModel

abstract class AbstractRoomModelConverter<M : Model, out R : RoomModel<M>> {
    abstract fun buildRoomModel(appModel: M): R
}
