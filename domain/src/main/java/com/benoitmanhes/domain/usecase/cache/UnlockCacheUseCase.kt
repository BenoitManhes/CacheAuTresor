package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import javax.inject.Inject

class UnlockCacheUseCase @Inject constructor(
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val cacheRepository: CacheRepository,
    private val userProgressRepository: CacheUserProgressRepository,
) : CTUseCase() {
    suspend operator fun invoke(lockCodeInput: String, cacheId: String): CTSuspendResult<Unit> = runCatchSuspendResult {
        val myExplorerId = getMyExplorerIdUseCase()
        val cache = cacheRepository.getCache(cacheId) ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()
        val userProgress = userProgressRepository.getFetchedCacheUserProgress(
            cacheId = cacheId,
            explorerId = myExplorerId
        )

        when {
            userProgress != null -> {}
            lockCodeInput.lowercase() != cache.lockCode.lowercase() -> throw CTDomainError.Code.CACHE_INVALID_UNLOCK_CODE.error()
            else -> {
                userProgressRepository.saveCacheUserProgress(
                    CacheUserProgress(
                        id = "$myExplorerId-$cacheId",
                        explorerId = myExplorerId,
                        cacheId = cacheId,
                        currentStepRef = null,
                    )
                )
            }
        }
        return@runCatchSuspendResult CTSuspendResult.Success(Unit)
    }
}
