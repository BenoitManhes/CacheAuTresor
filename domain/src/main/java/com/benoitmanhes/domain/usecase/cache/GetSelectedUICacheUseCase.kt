package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserData
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
    private val getUIStepUseCase: GetUIStepUseCase,
) : AbstractUseCase() {
    operator fun invoke(cacheId: String): Flow<CTResult<UICacheDetails>> = useCaseFlow {
        val cache = cacheRepository.getCache(cacheId) ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()
        val userData = CacheUserData(cacheId) // TODO complete
        val uiStep = getCacheStepsRefs(cache, userData).map {
            getUIStepUseCase(stepId = it, userData = userData)
        }

        getMyExplorerUseCase().collect { myExplorer ->
            val uiCacheDetails = UICacheDetails(
                cache = cache,
                explorerName = cache.getCreatorName(),
                status = myExplorer.getCacheDetailsUserStatus(cache),
                steps = uiStep,
                currentStep = uiStep.first(), // TODO handle current step
            )
            emit(CTResult.Success(uiCacheDetails))
        }
    }

    private suspend fun Cache.getCreatorName(): String? = runCatch(onError = { null }) {
        explorerRepository.getExplorer(this.creatorId)?.name
    }

    private fun Explorer.getCacheDetailsUserStatus(cache: Cache) = when {
        cache.creatorId == this.explorerId -> UICacheDetails.Status.Owned
        this.cacheIdsFound.contains(cache.cacheId) -> UICacheDetails.Status.Found
        // Handle here cache started
        else -> UICacheDetails.Status.Started
        //        else -> UICacheDetails.Status.Available
    }

    private fun getCacheStepsRefs(cache: Cache, userData: CacheUserData): List<String> = when (cache) {
        is Cache.Classical -> listOf(cache.finalStepRef)
        is Cache.Mystery -> listOf(cache.enigmaStepRef, cache.finalStepRef)
        is Cache.Piste -> cache.intermediaryStepRefs + listOf(cache.finalStepRef)
        is Cache.Coop -> listOf(
            cache.crewStepRefs.firstOrNull { it == userData.coopStepRef } ?: cache.crewStepRefs.first(),
            cache.finalStepRef,
        )
    }
}
