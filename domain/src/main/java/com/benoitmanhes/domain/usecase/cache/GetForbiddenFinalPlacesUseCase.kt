package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class GetForbiddenFinalPlacesUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val stepRepository: StepRepository,
) : CTUseCase() {
    suspend operator fun invoke(): CTSuspendResult<List<Coordinates>> = runCatchSuspendResult(
        onError = { CTSuspendResult.Failure(it) },
    ) {
        val forbiddenCoordinates = cacheRepository.getAllCaches().mapNotNull { cache ->
            runCatchNullable {
                stepRepository.getStep(cache.finalStepRef)?.coordinates
            }
        }
        return@runCatchSuspendResult CTSuspendResult.Success(forbiddenCoordinates)
    }
}
