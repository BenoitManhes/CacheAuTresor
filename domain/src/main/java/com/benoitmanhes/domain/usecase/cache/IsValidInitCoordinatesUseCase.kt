package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.coordinates.CalculateDistanceUseCase
import com.benoitmanhes.domain.utils.DomainConstants
import javax.inject.Inject

class IsValidInitCoordinatesUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val distanceUseCase: CalculateDistanceUseCase,
) : CTUseCase() {
    suspend operator fun invoke(coordinates: Coordinates): Boolean = runCatch(
        onError = { false },
    ) {
        return@runCatch cacheRepository.getAllCaches().none { cache ->
            distanceUseCase(coordinates, cache.coordinates) <= DomainConstants.Cache.initialCoordinatesDistanceMin
        }
    }
}
