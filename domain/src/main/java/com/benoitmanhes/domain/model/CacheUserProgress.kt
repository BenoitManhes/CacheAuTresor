package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model

data class CacheUserProgress(
    val cacheId: String,
    val stepDoneRefs: Set<String> = emptySet(),
    val currentStepRef: String? = null,
    val clueUnlockedStepRef: Set<String> = emptySet(),
    val coopStepRef: String? = null,
) : Model
