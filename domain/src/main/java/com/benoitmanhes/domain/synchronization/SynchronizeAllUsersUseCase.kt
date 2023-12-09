package com.benoitmanhes.domain.synchronization

import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.interfaces.repository.SyncRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class SynchronizeAllUsersUseCase @Inject constructor(
    private val explorerRepository: ExplorerRepository,
    private val syncRepository: SyncRepository,
) : CTUseCase() {
    suspend operator fun invoke(): Unit = runCatch {
        if (syncRepository.needToSyncAllUsers()) {
            explorerRepository.fetchAllExplorers()
            syncRepository.resetLastSyncAllUsers()
        }
    }
}
