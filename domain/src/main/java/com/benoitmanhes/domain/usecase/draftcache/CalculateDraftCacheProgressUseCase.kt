package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class CalculateDraftCacheProgressUseCase @Inject constructor() : CTUseCase() {
    operator fun invoke(draftCache: DraftCache, steps: List<DraftCacheStep>): Float {
        val cacheProgress = listOf(
            draftCache.title,
            draftCache.coordinates,
            draftCache.difficulty,
            draftCache.ground,
            draftCache.size,
//            draftCache.cacheIdsRequired,
            draftCache.finalStepRef,
            draftCache.description,
            draftCache.lockDescription,
            draftCache.lockCode,
            draftCache.type,
        )
        val stepsProgress = steps.map { step ->
            listOf(
                step.instruction,
                step.validationCode,
                step.coordinates,
            )
        }.flatten()

        val totalProgress = cacheProgress + stepsProgress

        val scoreMax = totalProgress.count().coerceAtLeast(1)
        val currentScore = totalProgress.filterNotNull().count()
        return currentScore.toFloat() / scoreMax.toFloat()
    }
}
