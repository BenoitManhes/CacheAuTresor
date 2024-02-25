package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.model.ForbiddenZone
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.utils.DomainConstants
import javax.inject.Inject

class GetForbiddenInitPlacesUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
) : CTUseCase() {
    suspend operator fun invoke(): CTSuspendResult<List<ForbiddenZone>> = runCatchSuspendResult(
        onError = { CTSuspendResult.Failure(it) },
    ) {
        val forbiddenCoordinates = cacheRepository.getAllCaches().map { cache ->
            ForbiddenZone(
                center = cache.coordinates,
                radius = DomainConstants.Cache.initialCoordinatesDistanceMin,
            )
        }
        return@runCatchSuspendResult CTSuspendResult.Success(forbiddenCoordinates)
    }
}
