package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveDraftStepUseCase @Inject constructor(
    private val updateDraftCacheProgressUseCase: UpdateDraftCacheProgressUseCase,
    private val draftCacheStepRepository: DraftCacheStepRepository,
) : CTUseCase() {
    operator fun invoke(draftCacheId: String, draftStep: DraftCacheStep): Flow<CTResult<Unit>> = useCaseFlow {
        draftCacheStepRepository.saveDraftCacheStep(draftStep)
        updateDraftCacheProgressUseCase(draftCacheId)
        emit(CTResult.Success(Unit))
    }
}
