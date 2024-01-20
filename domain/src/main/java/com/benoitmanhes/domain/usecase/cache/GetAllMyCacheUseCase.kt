package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

class GetAllMyCacheUseCase @Inject constructor(
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val cacheRepository: CacheRepository,
    private val explorerRepository: ExplorerRepository,
) : CTUseCase() {

    operator fun invoke(): Flow<CTResult<List<Pair<Cache, Int>>>> = useCaseFlow {
        emitAll(
            combine(
                explorerRepository.getUserExplorerFlow(getMyExplorerIdUseCase(), fetch = false),
                cacheRepository.getAllCachesByExplorer(getMyExplorerIdUseCase()),
            ) { myExplorer, myCaches ->
                val cachesWithPoints = myCaches
                    .sortedByDescending { cache -> cache.createDate }
                    .map { cache ->
                        cache to myExplorer.cachesMap.getOrDefault(cache.cacheId, 0)
                    }
                CTResult.Success(cachesWithPoints)
            }
        )
    }
}