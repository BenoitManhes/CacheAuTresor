package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.domain.model.ForbiddenZone
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.utils.DomainConstants
import javax.inject.Inject

class GetForbiddenFinalPlacesUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val stepRepository: StepRepository,
) : CTUseCase() {
    suspend operator fun invoke(): CTSuspendResult<List<ForbiddenZone>> = runCatchSuspendResult(
        onError = { CTSuspendResult.Failure(it) },
    ) {
        val finalStepRefs = cacheRepository.getAllCaches().map { it.finalStepRef }
        val finalSteps = stepRepository.getSteps(finalStepRefs)
        val forbiddenCoordinates = finalSteps.map { step ->
            ForbiddenZone(
                center = step.coordinates,
                radius = DomainConstants.Cache.finalCoordinatesDistanceMin,
            )
        }
        return@runCatchSuspendResult CTSuspendResult.Success(forbiddenCoordinates)
    }
}
