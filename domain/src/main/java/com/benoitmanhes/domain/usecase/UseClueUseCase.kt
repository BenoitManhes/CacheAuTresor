package com.benoitmanhes.domain.usecase

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import javax.inject.Inject

class UseClueUseCase @Inject constructor(
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val cacheUserProgressRepository: CacheUserProgressRepository,
) : CTUseCase() {

    suspend operator fun invoke(cacheStepId: String, cacheId: String): CTSuspendResult<Unit> = runCatchSuspendResult {
        val myExplorerId = getMyExplorerIdUseCase()
        val cacheProgress = cacheUserProgressRepository.getFetchedCacheUserProgress(
            explorerId = myExplorerId,
            cacheId = cacheId
        )
            ?: throw CTDomainError.Code.UNEXPECTED.error()
        if (!cacheProgress.clueUnlockedStepRef.contains(cacheStepId)) {
            val progressUpdated = cacheProgress.copy(
                clueUnlockedStepRef = buildSet {
                    addAll(cacheProgress.clueUnlockedStepRef)
                    add(cacheStepId)
                }
            )
            cacheUserProgressRepository.saveCacheUserProgress(progressUpdated)
        }
        CTSuspendResult.Success(Unit)
    }
}
