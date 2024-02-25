package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.coordinates.CalculateDistanceUseCase
import com.benoitmanhes.domain.utils.DomainConstants
import javax.inject.Inject

class IsValidFinalCoordinatesUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val cacheStepRepository: StepRepository,
    private val distanceUseCase: CalculateDistanceUseCase,
) : CTUseCase() {
    suspend operator fun invoke(coordinates: Coordinates): Boolean {
        val finalStepRef = cacheRepository.getAllCaches().map { it.finalStepRef }
        val finalSteps = cacheStepRepository.getSteps(finalStepRef)
        return finalSteps.none { finalStep ->
            distanceUseCase(coordinates, finalStep.coordinates) <= DomainConstants.Cache.finalCoordinatesDistanceMin
        }
    }
}
