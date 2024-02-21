package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.cache.IsValidInitCoordinatesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangeInitCoordinatesDraftCacheUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
    private val isValidInitCoordinatesUseCase: IsValidInitCoordinatesUseCase,
) : CTUseCase() {
    operator fun invoke(draftCacheId: String, newCoordinates: Coordinates?): Flow<CTResult<Unit>> = useCaseFlow {
        val validity = newCoordinates?.let { isValidInitCoordinatesUseCase(it) } == true
        if (!validity || newCoordinates == null) throw CTDomainError.Code.INITIAL_COORDINATES_INVALID.error()

        val draftCache = draftCacheRepository.getDraftCache(draftCacheId) ?: throw CTDomainError.Code.UNEXPECTED.error()

        when (draftCache.type) {
            DraftCache.Type.Classical -> draftCache.finalStepRef?.updateStepCoordinates(newCoordinates)
            is DraftCache.Type.Mystery -> draftCache.type.enigmaDraftStepId?.updateStepCoordinates(newCoordinates)
            else -> {} /* no-op */
        }

        saveDraftCacheUseCase(draftCache.copy(coordinates = newCoordinates))
        emit(CTResult.Success(Unit))
    }

    private suspend fun String.updateStepCoordinates(coordinates: Coordinates) {
        draftCacheStepRepository.getDraftCacheStep(this)?.let { draftStep ->
            draftCacheStepRepository.saveDraftCacheStep(draftStep.copy(coordinates = coordinates))
        }
    }
}
