package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserDataRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSelectedUICacheUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val explorerRepository: ExplorerRepository,
    private val getMyExplorerUseCase: GetMyExplorerUseCase,
    private val getUIStepUseCase: GetUIStepUseCase,
    private val cacheUserDataRepository: CacheUserDataRepository,
    private val cacheUserProgressRepository: CacheUserProgressRepository,
    private val authRepository: AuthRepository,
    useCaseImpl: CTUseCaseImpl,
) : CTUseCase by useCaseImpl {
    operator fun invoke(cacheId: String): Flow<CTResult<UICacheDetails>> = useCaseFlow {
        val myExplorerId = authRepository.getAuthAccount()?.explorerId ?: throw CTDomainError(
            CTDomainError.Code.NO_AUTHENTICATION
        )
        val cache = cacheRepository.getCache(cacheId) ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()

        combine(
            getMyExplorerUseCase(),
            cacheUserDataRepository.getCacheUserDataFlow(cacheId).map { it ?: CacheUserData(cacheId) },
            cacheUserProgressRepository.getCacheUserProgressFlow(explorerId = myExplorerId, cacheId = cacheId).map {
                it ?: CacheUserProgress(explorerId = myExplorerId, cacheId = cacheId, id = "$myExplorerId-$cacheId")
            },
        ) { myExplorer, userData, userProgress ->
            val uiSteps = getCacheStepsRefs(cache, userProgress).map { stepId ->
                getUIStepUseCase(
                    stepId = stepId,
                    userProgress = userProgress,
                )
            }
            val uiCacheDetails = UICacheDetails(
                cache = cache,
                explorerName = cache.getCreatorName(),
                status = myExplorer.getCacheDetailsUserStatus(cache, userData, userProgress),
                steps = uiSteps,
                currentStep = uiSteps.firstOrNull { it.status == UIStep.Status.Current } ?: uiSteps.last(),
                cacheProgress = userProgress,
                userData = userData,
            )
            emit(CTResult.Success(uiCacheDetails))
        }.collect()
    }.flowOn(Dispatchers.IO)

    private suspend fun Cache.getCreatorName(): String? = runCatch(onError = { null }) {
        explorerRepository.getExplorer(this.creatorId)?.name
    }

    private fun Explorer.getCacheDetailsUserStatus(cache: Cache, userData: CacheUserData, userProgress: CacheUserProgress) = when {
        cache.creatorId == this.explorerId -> UICacheDetails.Status.Owned
        userProgress.foundDate != null -> UICacheDetails.Status.Found
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
