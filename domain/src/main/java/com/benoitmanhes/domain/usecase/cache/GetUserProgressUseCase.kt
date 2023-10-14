package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserProgressUseCase @Inject constructor(
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val cacheUserProgressRepository: CacheUserProgressRepository,
) : CTUseCase() {
    suspend operator fun invoke(cacheId: String): Flow<CacheUserProgress?> {
        val myExplorerId = getMyExplorerIdUseCase()
        return cacheUserProgressRepository.getCacheUserProgressFlow(explorerId = myExplorerId, cacheId = cacheId)
    }
}
