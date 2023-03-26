package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model

data class Explorer(
    val explorerId: String,
    val name: String,
    val cacheIdsFound: List<String>,
) : Model
