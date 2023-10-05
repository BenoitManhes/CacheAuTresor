package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserDataRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.uimodel.UICacheDetails
import com.benoitmanhes.domain.uimodel.UIStep
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.CTUseCaseImpl
import com.benoitmanhes.domain.usecase.explorer.GetMyExplorerUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSelectedUICacheUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val explorerRepository: ExplorerRepository,
    private val getMyExplorerUseCase: GetMyExplorerUseCase,
    private val getUIStepUseCase: GetUIStepUseCase,
    private val cacheUserDataRepository: CacheUserDataRepository,
    useCaseImpl: CTUseCaseImpl,
) : CTUseCase by useCaseImpl {
    operator fun invoke(cacheId: String): Flow<CTResult<UICacheDetails>> = useCaseFlow {
        val cache = cacheRepository.getCache(cacheId) ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()
        val userProgress = CacheUserProgress(cacheId) // TODO userProgress
        val uiStep = getCacheStepsRefs(cache, userProgress).map { stepId ->
            getUIStepUseCase(
                stepId = stepId,
                userProgress = userProgress,
            )
        }

        combine(
            getMyExplorerUseCase(),
            cacheUserDataRepository.getCacheUserDataFlow(cacheId).map { it ?: CacheUserData(cacheId) },
        ) { myExplorer, userData ->
            val uiCacheDetails = UICacheDetails(
                cache = cache,
                explorerName = cache.getCreatorName(),
                status = myExplorer.getCacheDetailsUserStatus(cache, userData),
                steps = uiStep,
                currentStep = uiStep.firstOrNull { it.status == UIStep.Status.Current } ?: uiStep.last(),
            )
            emit(CTResult.Success(uiCacheDetails))
        }.collect()
    }

    private suspend fun Cache.getCreatorName(): String? = runCatch(onError = { null }) {
        explorerRepository.getExplorer(this.creatorId)?.name
    }

    private fun Explorer.getCacheDetailsUserStatus(cache: Cache, userData: CacheUserData) = when {
        cache.creatorId == this.explorerId -> UICacheDetails.Status.Owned
        this.cacheIdsFound.contains(cache.cacheId) -> UICacheDetails.Status.Found
        userData.isStarted -> UICacheDetails.Status.Started
        else -> UICacheDetails.Status.Available
    }

    private fun getCacheStepsRefs(cache: Cache, userProgress: CacheUserProgress): List<String> = when (cache) {
        is Cache.Classical -> listOf(cache.finalStepRef)
        is Cache.Mystery -> listOf(cache.enigmaStepRef, cache.finalStepRef)
        is Cache.Piste -> cache.intermediaryStepRefs + listOf(cache.finalStepRef)
        is Cache.Coop -> listOf(
            cache.crewStepRefs.firstOrNull { it == userProgress.coopStepRef } ?: cache.crewStepRefs.first(),
            cache.finalStepRef,
        )
    }
}
