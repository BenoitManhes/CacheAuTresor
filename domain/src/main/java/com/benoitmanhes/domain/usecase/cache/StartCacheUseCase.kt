package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.CacheUserDataRepository
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.CTUseCaseImpl
import javax.inject.Inject

class StartCacheUseCase @Inject constructor(
    private val userDataRepository: CacheUserDataRepository,
    useCaseImpl: CTUseCaseImpl,
) : CTUseCase by useCaseImpl {
    suspend operator fun invoke(cacheId: String): CTSuspendResult<Unit> = runCatchSuspendResult {
        val userData = userDataRepository.getCacheUserData(cacheId) ?: CacheUserData(cacheId)
        userDataRepository.saveCacheUserData(userData.copy(isStarted = true))
        CTSuspendResult.Success(Unit)
    }
}
