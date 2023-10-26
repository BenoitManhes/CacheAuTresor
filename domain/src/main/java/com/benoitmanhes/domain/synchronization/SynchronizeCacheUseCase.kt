package com.benoitmanhes.domain.synchronization

import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.SyncRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class SynchronizeCacheUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val syncRepository: SyncRepository,
) : CTUseCase() {
    suspend operator fun invoke(): Unit = runCatch {
        if (syncRepository.needToSyncCache()) {
            cacheRepository.fetchAllCaches()
            syncRepository.resetLastSyncCache()
        }
    }
}
