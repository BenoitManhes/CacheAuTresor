package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.uimodel.UIDraftStepDetail
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.cache.IsValidFinalCoordinatesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

class ChangeStepCoordinatesUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val saveDraftStepUseCase: SaveDraftStepUseCase,
    private val isValidFinalCoordinatesUseCase: IsValidFinalCoordinatesUseCase,
    private val getDraftStepTypeUseCase: GetDraftStepTypeUseCase,
) : CTUseCase() {
    operator fun invoke(draftCacheId: String, draftStepId: String, newCoordinates: Coordinates?): Flow<CTResult<Unit>> = useCaseFlow {
        val draftCache = draftCacheRepository.getDraftCache(draftCacheId) ?: throw CTDomainError.Code.UNEXPECTED.error()
        val draftStep = draftCacheStepRepository.getDraftCacheStep(draftStepId) ?: throw CTDomainError.Code.UNEXPECTED.error()
        val stepType = getDraftStepTypeUseCase(draftCache, draftStepId)

        // Check final coordinates validity
        if (stepType is UIDraftStepDetail.Type.Final) {
            val validity = newCoordinates?.let { isValidFinalCoordinatesUseCase(it) } == true
            if (!validity) throw CTDomainError.Code.INVALID_COORDINATES.error()
        }

        emitAll(
            saveDraftStepUseCase(
                draftCacheId = draftCacheId,
                draftStep = draftStep.copy(coordinates = newCoordinates),
            )
        )
    }
}
