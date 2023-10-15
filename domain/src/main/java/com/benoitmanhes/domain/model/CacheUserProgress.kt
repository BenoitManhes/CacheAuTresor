package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import java.util.Date

data class CacheUserProgress(
    val id: String,
    val explorerId: String,
    val cacheId: String,
    val stepDoneRefs: Set<String> = emptySet(),
    val currentStepRef: String? = null,
    val clueUnlockedStepRef: Set<String> = emptySet(),
    val coopMemberRef: String? = null,
    val ptsWin: Int? = null,
    val foundDate: Date? = null,
) : Model
