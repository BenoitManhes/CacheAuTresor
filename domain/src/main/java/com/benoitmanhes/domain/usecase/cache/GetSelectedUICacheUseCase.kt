package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.uimodel.UICacheDetails
import com.benoitmanhes.domain.usecase.AbstractUseCase
import com.benoitmanhes.domain.usecase.explorer.GetMyExplorerUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedUICacheUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val explorerRepository: ExplorerRepository,
    private val getMyExplorerUseCase: GetMyExplorerUseCase,
) : AbstractUseCase() {
    operator fun invoke(cacheId: String): Flow<CTResult<UICacheDetails>> = useCaseFlow {
        val cache = cacheRepository.getCache(cacheId) ?: throw CTDomainError(CTDomainError.Code.CACHE_NOT_FOUND)

        getMyExplorerUseCase().collect { myExplorer ->
            val uiCacheDetails = UICacheDetails(
                cache = cache,
                explorerName = cache.getCreatorName(),
                userStatus = myExplorer.getCacheDetailsUserStatus(cache)
            )
            emit(CTResult.Success(uiCacheDetails))
        }
    }

    private suspend fun Cache.getCreatorName(): String? = runCatch(onError = { null }) {
        explorerRepository.getExplorer(this.creatorId)?.name
    }

    private fun Explorer.getCacheDetailsUserStatus(cache: Cache) = when {
        cache.creatorId == this.explorerId -> UICacheDetails.CacheDetailsUserStatus.Owned
        this.cacheIdsFound.contains(cache.cacheId) -> UICacheDetails.CacheDetailsUserStatus.Found
        // Handle here cache started
        else -> UICacheDetails.CacheDetailsUserStatus.Available
    }
}
