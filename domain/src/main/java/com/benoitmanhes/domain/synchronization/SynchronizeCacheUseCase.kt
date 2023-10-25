package com.benoitmanhes.domain.synchronization

import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class SynchronizeCacheUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
) : CTUseCase() {
    suspend operator fun invoke(): Unit = runCatch {
        cacheRepository.fetchAllCaches()
    }
}
