package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import java.util.Date

data class Explorer(
    val explorerId: String,
    val name: String,
    val cachesMap: Map<String, Int>,
    val cachesFoundMap: Map<String, Int>,
    val creationDate: Date,
) : Model
