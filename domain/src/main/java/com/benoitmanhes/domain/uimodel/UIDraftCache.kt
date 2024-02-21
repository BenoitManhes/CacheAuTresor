package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep

data class UIDraftCache(
    val draftCache: DraftCache,
    val steps: Steps?,
) {
    sealed interface Steps {
        data class Classical(
            val instruction: DraftCacheStep,
        ) : Steps

        data class Mystery(
            val enigma: DraftCacheStep,
            val finalStep: DraftCacheStep,
        ) : Steps

        data class Piste(
            val intermediarySteps: List<DraftCacheStep>,
            val finalStep: DraftCacheStep,
        ) : Steps

        data class Coop(
            val crewSteps: Map<String, List<DraftCacheStep>>,
            val finalStep: DraftCacheStep,
        ) : Steps
    }
}
