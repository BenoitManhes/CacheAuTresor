package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.uimodel.UIDraftStepDetail
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

class GetUIDraftStepDetailUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val getDraftStepTypeUseCase: GetDraftStepTypeUseCase,
) : CTUseCase() {

    operator fun invoke(draftCacheId: String, draftStepId: String): Flow<CTResult<UIDraftStepDetail>> = useCaseFlow {
        emitAll(
            combine(
                draftCacheRepository.getDraftCacheFlow(draftCacheId),
                draftCacheStepRepository.getDraftCacheStepFlow(dratCacheStepId = draftStepId),
            ) { draftCache, draftStep ->
                draftCache ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()
                draftStep ?: throw CTDomainError.Code.CACHE_STEP_NOT_FOUND.error()

                val uiDraftStepDetail = UIDraftStepDetail(
                    draftCache = draftCache,
                    draftStep = draftStep,
                    type = getDraftStepTypeUseCase(draftCache, draftStepId),
                )
                CTResult.Success(uiDraftStepDetail)
            }
        )
    }
}
