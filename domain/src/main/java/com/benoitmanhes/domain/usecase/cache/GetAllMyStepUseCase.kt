package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserProgress
import javax.inject.Inject

class GetAllMyStepUseCase @Inject constructor() {
    operator fun invoke(cache: Cache, userProgress: CacheUserProgress): List<String> = when (cache.type) {
        is Cache.Type.Classical -> listOf(cache.finalStepRef)
        is Cache.Type.Mystery -> listOf(cache.type.enigmaStepId, cache.finalStepRef)
        is Cache.Type.Piste -> cache.type.intermediateStepIds + cache.finalStepRef
        is Cache.Type.Coop -> cache.type.crewStepsMap[userProgress.coopMemberRef]!! + cache.finalStepRef
    }
}
