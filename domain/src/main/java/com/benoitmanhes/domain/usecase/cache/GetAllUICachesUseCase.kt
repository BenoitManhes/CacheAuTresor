package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.uimodel.UIExploreCache
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.CTUseCaseImpl
import com.benoitmanhes.domain.usecase.explorer.GetMyExplorerUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUICachesUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val explorerRepository: ExplorerRepository,
    private val getMyExplorerUseCase: GetMyExplorerUseCase,
    useCaseImpl: CTUseCaseImpl,
) : CTUseCase by useCaseImpl {

    operator fun invoke(): Flow<CTResult<List<UIExploreCache>>> = useCaseFlow {
        val allCaches = cacheRepository.getAllCaches()

        getMyExplorerUseCase().collect { myExplorer ->
            val uiExploreCaches = allCaches
                .filter { cache -> cache.isAvailableForExplorer(myExplorer) }
                .map { cache ->
                    UIExploreCache(
                        cache = cache,
                        explorerName = cache.getCreatorName(),
                        userStatus = cache.getUserStatus(myExplorer),
                        distance = null,
                    )
                }
            emit(CTResult.Success(uiExploreCaches))
        }
    }

    private fun Cache.isAvailableForExplorer(explorer: Explorer) = getUserStatus(explorer) != UIExploreCache.CacheUserStatus.Lock

    private fun Cache.getUserStatus(explorer: Explorer): UIExploreCache.CacheUserStatus = when {
        explorer.explorerId == this.creatorId -> UIExploreCache.CacheUserStatus.Owned
        explorer.cacheIdsFound.contains(this.cacheId) -> UIExploreCache.CacheUserStatus.Found
        explorer.cacheIdsFound.containsAll(this.cacheIdsRequired) -> UIExploreCache.CacheUserStatus.Available
        else -> UIExploreCache.CacheUserStatus.Lock
    }

    private suspend fun Cache.getCreatorName(): String? = runCatch(onError = { null }) {
        explorerRepository.getExplorer(this.creatorId)?.name
    }
}
