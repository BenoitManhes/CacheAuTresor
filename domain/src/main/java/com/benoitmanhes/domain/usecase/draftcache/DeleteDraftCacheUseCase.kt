package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteDraftCacheUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val draftCacheGetAllStepUseCase: DraftCacheGetAllStepUseCase,
) : CTUseCase() {
    operator fun invoke(draftCacheId: String): Flow<CTResult<Unit>> = useCaseFlow {
        val draftCache = draftCacheRepository.getDraftCache(draftCacheId) ?: run {
            emit(CTResult.Success(Unit))
            return@useCaseFlow
        }

        val stepsRef = draftCacheGetAllStepUseCase.idsOnly(draftCache)

        draftCacheRepository.deleteDraftCache(draftCacheId)
        draftCacheStepRepository.deleteDraftCacheSteps(stepsRef)

        emit(CTResult.Success(Unit))
    }
}
