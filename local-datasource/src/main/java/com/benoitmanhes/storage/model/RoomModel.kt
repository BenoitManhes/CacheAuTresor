package com.benoitmanhes.storage.model

import com.benoitmanhes.domain.interfaces.Model
import com.benoitmanhes.domain.interfaces.ModelEncodable

interface RoomModel<M : Model> : ModelEncodable<M>
