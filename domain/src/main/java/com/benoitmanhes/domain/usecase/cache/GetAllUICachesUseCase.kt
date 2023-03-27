package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.uimodel.UICache
import com.benoitmanhes.domain.usecase.AbstractUseCase
import com.benoitmanhes.domain.usecase.common.CalculateDistanceUseCase
import com.benoitmanhes.domain.usecase.explorer.GetMyExplorerUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUICachesUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val explorerRepository: ExplorerRepository,
    private val getMyExplorerUseCase: GetMyExplorerUseCase,
) : AbstractUseCase() {

    operator fun invoke(): Flow<CTResult<List<UICache>>> = useCaseFlow {
        val allCaches = cacheRepository.getAllCaches()

        getMyExplorerUseCase().collect { myExplorer ->
            val uiCaches = allCaches
                .filter { cache -> cache.isAvailableForExplorer(myExplorer) }
                .map { cache ->
                    UICache(
                        cache = cache,
                        explorerName = cache.getCreatorName(),
                        userStatus = cache.getUserStatus(myExplorer),
                        distance = null,
                    )
                }
            emit(CTResult.Success(uiCaches))
        }
    }

    private fun Cache.isAvailableForExplorer(explorer: Explorer) = getUserStatus(explorer) != UICache.CacheUserStatus.Lock

    private fun Cache.getUserStatus(explorer: Explorer): UICache.CacheUserStatus = when {
        explorer.explorerId == this.creatorId -> UICache.CacheUserStatus.Owned
        explorer.cacheIdsFound.contains(this.cacheId) -> UICache.CacheUserStatus.Found
        explorer.cacheIdsFound.containsAll(this.cacheIdsRequired) -> UICache.CacheUserStatus.Available
        else -> UICache.CacheUserStatus.Lock
    }

    private suspend fun Cache.getCreatorName(): String? = runCatch(onError = { null }) {
        explorerRepository.getExplorer(this.creatorId)?.name
    }
}