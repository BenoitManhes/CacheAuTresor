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
import com.benoitmanhes.domain.model.CacheUserStatus
import com.benoitmanhes.domain.uimodel.UICacheDetails
import com.benoitmanhes.domain.uimodel.UIStep
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSelectedUICacheUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val explorerRepository: ExplorerRepository,
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val getUIStepsUseCase: GetUIStepsUseCase,
    private val cacheUserDataRepository: CacheUserDataRepository,
    private val getUserProgressUseCase: GetUserProgressUseCase,
) : CTUseCase() {

    operator fun invoke(cacheId: String): Flow<CTResult<UICacheDetails>> = useCaseFlow {
        val cache = cacheRepository.getCache(cacheId) ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()

        combine(
            cacheUserDataRepository.getCacheUserDataFlow(cacheId).map { it ?: CacheUserData(cacheId) },
            getUserProgressUseCase(cacheId),
        ) { userData, userProgress ->
            val status = getCacheDetailsUserStatus(
                explorerId = getMyExplorerIdUseCase(),
                cache = cache,
                userProgress = userProgress,
            )

            val uiCacheDetails = UICacheDetails(
                cache = cache,
                explorerName = cache.getCreatorName(),
                status = status,
                steps = getCacheStepsRefs(cache, userProgress).map {
                    getUIStepsUseCase(it, cache, userProgress, status.cacheUserStatus)
                }.filterNot {
                    it.status == UIStep.Status.Lock
                        && it.type == UIStep.Type.Final
                        && status.cacheUserStatus == CacheUserStatus.Started
                },
                userData = userData,
            )

            emit(CTResult.Success(uiCacheDetails))
        }.collect()
    }

    private suspend fun Cache.getCreatorName(): String? = runCatch(onError = { null }) {
        explorerRepository.getExplorer(this.creatorId)?.name
    }

    private fun getCacheDetailsUserStatus(
        explorerId: String,
        cache: Cache,
        userProgress: CacheUserProgress?,
    ): UICacheDetails.Status = when {
        cache.creatorId == explorerId -> UICacheDetails.Status.Owned
        userProgress?.currentStepRef == null -> UICacheDetails.Status.Available
        userProgress.foundDate != null -> UICacheDetails.Status.Found(
            foundDate = userProgress.foundDate,
            pts = userProgress.ptsWin
        )

        else -> UICacheDetails.Status.Started(userProgress)
    }

    private fun getCacheStepsRefs(cache: Cache, userProgress: CacheUserProgress?): List<String> = when (cache.type) {
        is Cache.Type.Classical -> listOf(cache.finalStepRef)
        is Cache.Type.Mystery -> listOf(cache.type.enigmaStepId, cache.finalStepRef)
        is Cache.Type.Piste -> cache.type.intermediateStepIds + listOf(cache.finalStepRef)
        is Cache.Type.Coop -> buildList {
            val myCrewSteps = userProgress?.let {
                cache.type.crewStepsMap[userProgress.coopMemberRef]
            }.orEmpty()
            addAll(myCrewSteps)
            add(cache.finalStepRef)
        }
    }
}
