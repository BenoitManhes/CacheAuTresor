package com.benoitmanhes.domain.synchronization

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.interfaces.repository.SyncRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import com.benoitmanhes.domain.usecase.explorer.UpdateCartographyPointsUseCase
import com.benoitmanhes.domain.usecase.explorer.UpdateExplorationPointsUseCase
import javax.inject.Inject

class SynchronizeUserUseCase @Inject constructor(
    private val explorerRepository: ExplorerRepository,
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val updateCartographyPointsUseCase: UpdateCartographyPointsUseCase,
    private val updateExplorationPointsUseCase: UpdateExplorationPointsUseCase,
    private val syncRepository: SyncRepository,
) : CTUseCase() {

    suspend operator fun invoke(): Unit = runCatch {
        val myExplorerId = getMyExplorerIdUseCase()
        val myExplorer = explorerRepository.getExplorerFetched(myExplorerId) ?: throw CTDomainError.Code.EXPLORER_NOT_FOUND.error()
        if (syncRepository.needToSyncUserPoints()) {
            updateExplorationPointsUseCase(explorer = myExplorer)
            updateCartographyPointsUseCase(
                explorer = myExplorer,
                cacheIds = myExplorer.cachesMap.keys.toList(),
            )
            syncRepository.resetLastSyncUserPoints()
        }
    }
}
