package com.benoitmanhes.domain.usecase.explorer

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class UpdateExplorationPointsUseCase @Inject constructor(
    private val explorerRepository: ExplorerRepository,
    private val progressRepository: CacheUserProgressRepository,
) : CTUseCase() {

    suspend operator fun invoke(explorerId: String): Unit = runCatch {
        val explorer = explorerRepository.getExplorerFetched(explorerId = explorerId)
            ?: throw CTDomainError.Code.EXPLORER_NOT_FOUND.error()
        this(explorer = explorer)
    }

    suspend operator fun invoke(explorer: Explorer): Unit = runCatch {
        val explorationPoints = progressRepository.getAllUserProgressRemote(
            explorerId = explorer.explorerId,
            remoteOnly = false,
        )
            .filter { it.ptsWin != null }
            .associate { userProgress ->
                userProgress.cacheId to userProgress.ptsWin!!
            }
        if (explorer.cachesFoundMap != explorationPoints) {
            explorerRepository.saveExplorer(
                explorer = explorer.copy(cachesFoundMap = explorationPoints),
            )
        }
    }
}
