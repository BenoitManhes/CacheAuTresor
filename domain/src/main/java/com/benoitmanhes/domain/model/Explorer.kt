package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model

data class Explorer(
    val explorerId: String,
    val name: String,
    val cachesMap: Map<String, Int>,
    val explorationPts: Int,
) : Model
