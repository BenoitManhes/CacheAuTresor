package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import javax.inject.Inject

class StartCoopCacheUseCase @Inject constructor(
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val userProgressRepository: CacheUserProgressRepository,
    private val cacheRepository: CacheRepository,
) : CTUseCase() {

    suspend operator fun invoke(cacheId: String, crewPosition: String): CTSuspendResult<Unit> = runCatchSuspendResult {
        val myExplorerId = getMyExplorerIdUseCase()
        val cache = cacheRepository.getCache(cacheId) ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()
        val cacheType = cache.type as Cache.Type.Coop
        val userProgress = userProgressRepository.getFetchedCacheUserProgress(
            cacheId = cacheId,
            explorerId = myExplorerId,
        ) ?: throw CTDomainError.Code.UNEXPECTED.error()
        userProgressRepository.saveCacheUserProgress(
            userProgress.copy(
                currentStepRef = cacheType.crewStepsMap[crewPosition]?.first() ?: throw CTDomainError.Code.UNEXPECTED.error(),
                coopMemberRef = crewPosition,
            )
        )
        CTSuspendResult.Success(Unit)
    }
}
