package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserProgress
import javax.inject.Inject

class GetAllMyStepUseCase @Inject constructor() {
    operator fun invoke(cache: Cache, userProgress: CacheUserProgress): List<String> = when (cache) {
        is Cache.Classical -> listOf(cache.finalStepRef)
        is Cache.Mystery -> listOf(cache.enigmaStepRef, cache.finalStepRef)
        is Cache.Piste -> cache.intermediaryStepRefs + cache.finalStepRef
        is Cache.Coop -> cache.crewStepRefs[userProgress.coopMemberRef]!! + cache.finalStepRef
    }
}
