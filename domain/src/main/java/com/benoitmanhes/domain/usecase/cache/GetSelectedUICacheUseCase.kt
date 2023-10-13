package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserDataRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.uimodel.UICacheDetails
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
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
    private val getUIStepsUseCase: GetUIStepsUseCase,
    private val cacheUserDataRepository: CacheUserDataRepository,
    private val cacheUserProgressRepository: CacheUserProgressRepository,
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
) : CTUseCase() {

    operator fun invoke(cacheId: String): Flow<CTResult<UICacheDetails>> = useCaseFlow {
        val myExplorerId = getMyExplorerIdUseCase()
        val cache = cacheRepository.getCache(cacheId) ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()

        combine(
            getMyExplorerUseCase(),
            cacheUserDataRepository.getCacheUserDataFlow(cacheId).map { it ?: CacheUserData(cacheId) },
            cacheUserProgressRepository.getCacheUserProgressFlow(explorerId = myExplorerId, cacheId = cacheId),
        ) { myExplorer, userData, userProgress ->
            val uiCacheDetails = UICacheDetails(
                cache = cache,
                explorerName = cache.getCreatorName(),
                status = myExplorer.getCacheDetailsUserStatus(cache, userProgress),
                steps = getCacheStepsRefs(cache).map { getUIStepsUseCase(it, userProgress) },
                userData = userData,
            )
            emit(CTResult.Success(uiCacheDetails))
        }.collect()
    }

    private suspend fun Cache.getCreatorName(): String? = runCatch(onError = { null }) {
        explorerRepository.getExplorer(this.creatorId)?.name
    }

    private fun Explorer.getCacheDetailsUserStatus(cache: Cache, userProgress: CacheUserProgress?) = when {
        cache.creatorId == this.explorerId -> UICacheDetails.Status.Owned
        userProgress == null -> UICacheDetails.Status.Available
        userProgress.foundDate != null -> UICacheDetails.Status.Found(
            foundDate = userProgress.foundDate,
            pts = userProgress.ptsWin
        )
        else -> UICacheDetails.Status.Started(userProgress)
    }

    private fun getCacheStepsRefs(cache: Cache): List<String> = when (cache) {
        is Cache.Classical -> listOf(cache.finalStepRef)
        is Cache.Mystery -> listOf(cache.enigmaStepRef, cache.finalStepRef)
        is Cache.Piste -> cache.intermediaryStepRefs + listOf(cache.finalStepRef)
        is Cache.Coop -> buildList {
            add(cache.initialCrewStepRef)
            addAll(cache.crewStepRefs)
            add(cache.finalStepRef)
        }
    }
}
