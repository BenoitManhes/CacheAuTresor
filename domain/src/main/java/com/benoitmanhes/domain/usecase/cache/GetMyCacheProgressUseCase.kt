package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyCacheProgressUseCase @Inject constructor(
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val userProgressRepository: CacheUserProgressRepository,
) {
    suspend operator fun invoke(): Flow<List<CacheUserProgress>> =
        userProgressRepository.getAllCacheUserProgressFlow(
            explorerId = getMyExplorerIdUseCase(),
        )
}
