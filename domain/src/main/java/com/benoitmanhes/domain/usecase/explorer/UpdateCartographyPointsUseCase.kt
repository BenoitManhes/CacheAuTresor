package com.benoitmanhes.domain.usecase.explorer

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class UpdateCartographyPointsUseCase @Inject constructor(
    private val explorerRepository: ExplorerRepository,
    private val progressRepository: CacheUserProgressRepository,
) : CTUseCase() {
    suspend operator fun invoke(explorerId: String, cacheIds: List<String>, isMyExplorer: Boolean): Unit = runCatch {
        val explorer = explorerRepository.getExplorerFetched(explorerId = explorerId, remoteOnly = true)
            ?: throw CTDomainError.Code.EXPLORER_NOT_FOUND.error()
        this(explorer = explorer, cacheIds = cacheIds, isMyExplorer = isMyExplorer)
    }

    suspend operator fun invoke(explorer: Explorer, cacheIds: List<String>, isMyExplorer: Boolean): Unit = runCatch {
        val cachePoints = cacheIds.associateWith { cacheId ->
            progressRepository.getAllUserProgressByCache(cacheId).mapNotNull { it.ptsWin }.sum()
        }
        val mapUpdated = explorer.cachesMap.toMutableMap().apply {
            this.putAll(cachePoints)
        }

        if (explorer.cachesMap != mapUpdated) {
            explorerRepository.saveExplorer(
                explorer = explorer.copy(cachesMap = mapUpdated),
                remoteOnly = !isMyExplorer,
            )
        }
    }
}
