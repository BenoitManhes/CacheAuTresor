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

class StartCacheUseCase @Inject constructor(
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val userProgressRepository: CacheUserProgressRepository,
    private val cacheRepository: CacheRepository,
) : CTUseCase() {

    suspend operator fun invoke(cacheId: String): CTSuspendResult<Unit> = runCatchSuspendResult {
        val myExplorerId = getMyExplorerIdUseCase()
        val cache = cacheRepository.getCache(cacheId) ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()
        val userProgress = userProgressRepository.getFetchedCacheUserProgress(
            cacheId = cacheId,
            explorerId = myExplorerId
        ) ?: throw CTDomainError.Code.UNEXPECTED.error()
        userProgressRepository.saveCacheUserProgress(
            userProgress.copy(
                currentStepRef = cache.getInitialStepRef(),
            )
        )
        CTSuspendResult.Success(Unit)
    }

    private fun Cache.getInitialStepRef(): String = when (this.type) {
        is Cache.Type.Coop -> throw CTDomainError.Code.UNEXPECTED.error()
        is Cache.Type.Classical -> finalStepRef
        is Cache.Type.Piste -> type.intermediateStepIds.first()
        is Cache.Type.Mystery -> type.enigmaStepId
    }
}
