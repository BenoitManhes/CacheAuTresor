package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.uimodel.UIExploreCache
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetAllUICachesUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val explorerRepository: ExplorerRepository,
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val getMyCacheProgressUseCase: GetMyCacheProgressUseCase,
) : CTUseCase() {

    operator fun invoke(): Flow<CTResult<List<UIExploreCache>>> = useCaseFlow {
        val myExplorerId = getMyExplorerIdUseCase()
        combine(
            cacheRepository.getAllCachesFlow(),
            getMyCacheProgressUseCase(),
        ) { allCaches, myCachesProgress ->
            val uiExploreCaches = allCaches
                .map { cache ->
                    UIExploreCache(
                        cache = cache,
                        explorerName = cache.getCreatorName(),
                        userStatus = cache.getUserStatus(
                            explorerId = myExplorerId,
                            cachesProgress = myCachesProgress,
                        ),
                        distance = null,
                        ptsWin = myCachesProgress.firstOrNull { it.cacheId == cache.cacheId }?.ptsWin,
                    )
                }
                .filter { it.userStatus != UIExploreCache.CacheUserStatus.Lock }
            emit(CTResult.Success(uiExploreCaches))
        }.collect {}
    }

    private fun Cache.getUserStatus(explorerId: String, cachesProgress: List<CacheUserProgress>): UIExploreCache.CacheUserStatus {
        val cacheIdsFound = cachesProgress
            .filter { it.foundDate != null }
            .map { it.cacheId }
            .toSet()
        val cacheStarted = cachesProgress
            .map { it.cacheId }
            .toSet()

        return when {
            explorerId == this.creatorId -> UIExploreCache.CacheUserStatus.Owned
            cacheIdsFound.contains(this.cacheId) -> UIExploreCache.CacheUserStatus.Found
            !cacheIdsFound.containsAll(this.cacheIdsRequired) -> UIExploreCache.CacheUserStatus.Lock
            cacheStarted.contains(this.cacheId) -> UIExploreCache.CacheUserStatus.Started
            else -> UIExploreCache.CacheUserStatus.Available
        }
    }

    private suspend fun Cache.getCreatorName(): String? = runCatch(onError = { null }) {
        explorerRepository.getExplorer(this.creatorId)?.name
    }
}
