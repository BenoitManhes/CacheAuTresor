package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep
import javax.inject.Inject

class DraftCacheGetAllStepUseCase @Inject constructor(
    private val draftCacheStepRepository: DraftCacheStepRepository,
) {
    suspend operator fun invoke(draftCache: DraftCache): List<DraftCacheStep> =
        draftCacheStepRepository.getDraftCacheSteps(
            stepsIds = idsOnly(draftCache),
        )

    fun idsOnly(draftCache: DraftCache): List<String> {
        val intermediarySteps = draftCache.type?.let { type ->
            when (type) {
                is DraftCache.Type.Classical -> emptyList()
                is DraftCache.Type.Coop -> type.crewDraftStepsMap.values.flatten()
                is DraftCache.Type.Mystery -> listOfNotNull(type.enigmaDraftStepId)
                is DraftCache.Type.Piste -> type.intermediateDraftStepIds
            }
        } ?: emptyList()
        return (intermediarySteps + draftCache.finalStepRef).filterNotNull()
    }
}