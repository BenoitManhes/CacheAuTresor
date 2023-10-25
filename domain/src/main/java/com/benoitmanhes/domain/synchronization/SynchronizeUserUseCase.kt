package com.benoitmanhes.domain.synchronization

import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import javax.inject.Inject

class SynchronizeUserUseCase @Inject constructor(
    private val explorerRepository: ExplorerRepository,
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val userProgressRepository: CacheUserProgressRepository,
) : CTUseCase() {

    suspend operator fun invoke(): Unit = runCatch {
        val myExplorerId = getMyExplorerIdUseCase()
        explorerRepository.getExplorerFetched(myExplorerId)
        userProgressRepository.fetchedAllUserProgress(myExplorerId)
    }
}
