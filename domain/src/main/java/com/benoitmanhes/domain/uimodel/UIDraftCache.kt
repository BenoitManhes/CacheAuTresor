package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.DraftCache

data class UIDraftCache(
    val draftCache: DraftCache,
    val steps: List<UIDraftStep>,
)
