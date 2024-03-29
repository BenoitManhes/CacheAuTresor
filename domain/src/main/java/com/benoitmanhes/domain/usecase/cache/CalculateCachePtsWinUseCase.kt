package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.utils.DomainConstants
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.sqrt

class CalculateCachePtsWinUseCase @Inject constructor(
    private val getAllMyStepUseCase: GetAllMyStepUseCase,
) {
    operator fun invoke(cache: Cache, userProgress: CacheUserProgress): Int {
        val totalPts = cache.getScoreMax()
        val finalPenalty = cache.getFinalStepCoef(userProgress.clueUnlockedStepRef)
        val stepPenalty = cache.getIntermediaryStepCoef(userProgress)
        return (totalPts * finalPenalty * stepPenalty).toInt().coerceAtLeast(1)
    }

    private fun Cache.getScoreMax(): Float =
        ((difficulty + ground)) * max(difficulty, ground) * sqrt(getStepCoeficient().toFloat())

    private fun Cache.getStepCoeficient(): Int = when (type) {
        is Cache.Type.Classical -> listOf(finalStepRef).count()
        is Cache.Type.Mystery -> listOf(type.enigmaStepId, finalStepRef).count()
        is Cache.Type.Piste -> (type.intermediateStepIds + finalStepRef).count()
        is Cache.Type.Coop -> {
            type.crewStepsMap.values.flatten().count() / type.crewStepsMap.keys.count() + listOf(finalStepRef).count()
        }
    }

    private fun Cache.getFinalStepCoef(clueUsed: Set<String>): Float =
        if (clueUsed.contains(finalStepRef)) {
            DomainConstants.Cache.ptsWinPenaltyCoef
        } else {
            1f
        }

    private fun Cache.getIntermediaryStepCoef(userProgress: CacheUserProgress): Float {
        val intermediaryClueUsed = (userProgress.clueUnlockedStepRef - finalStepRef).count()
        val intermediaryStepCount = (getAllMyStepUseCase(this, userProgress) - finalStepRef).count()
        return if (intermediaryStepCount == 0) {
            1f
        } else {
            1 + (intermediaryClueUsed.toFloat() / intermediaryStepCount) * (DomainConstants.Cache.ptsWinPenaltyCoef - 1)
        }
    }
}
