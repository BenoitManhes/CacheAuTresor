package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.uimodel.UIDraftCache
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetUIDraftCacheUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val getUIDraftCacheStepsUseCase: GetUIDraftCacheStepsUseCase,
    private val getCacheCreationStep: GetCacheCreationStep,
) : CTUseCase() {

    operator fun invoke(draftCacheId: String): Flow<UIDraftCache?> = combine(
        draftCacheRepository.getDraftCacheFlow(draftCacheId),
        getUIDraftCacheStepsUseCase.asFlow(draftCacheId),
    ) { draftCache, steps ->
        draftCache?.let {
            UIDraftCache(
                draftCache = draftCache,
                steps = steps,
                creationStep = getCacheCreationStep(draftCache, steps),
            )
        }
    }.useCaseCatch { null }
}
