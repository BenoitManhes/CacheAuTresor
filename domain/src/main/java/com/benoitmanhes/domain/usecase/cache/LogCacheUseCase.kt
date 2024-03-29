package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import com.benoitmanhes.domain.usecase.explorer.UpdateCartographyPointsUseCase
import com.benoitmanhes.domain.usecase.explorer.UpdateExplorationPointsUseCase
import java.util.Date
import javax.inject.Inject

class LogCacheUseCase @Inject constructor(
    private val stepRepository: StepRepository,
    private val userProgressRepository: CacheUserProgressRepository,
    private val cacheRepository: CacheRepository,
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val getAllMyStepUseCase: GetAllMyStepUseCase,
    private val calculateCachePtsWinUseCase: CalculateCachePtsWinUseCase,
    private val updateExplorationPointsUseCase: UpdateExplorationPointsUseCase,
    private val updateCartographyPointsUseCase: UpdateCartographyPointsUseCase,
) : CTUseCase() {
    suspend operator fun invoke(cacheId: String, codeLog: String): CTSuspendResult<CacheUserProgress> = runCatchSuspendResult {
        val myExplorerId = getMyExplorerIdUseCase()
        val cache = cacheRepository.getCache(cacheId) ?: throw CTDomainError.Code.CACHE_NOT_FOUND.error()
        val userProgress = userProgressRepository.getFetchedCacheUserProgress(
            cacheId = cacheId,
            explorerId = myExplorerId
        ) ?: throw CTDomainError.Code.UNEXPECTED.error()
        val currentStep = userProgress.currentStepRef?.let {
            stepRepository.getStep(userProgress.currentStepRef)
        } ?: throw CTDomainError.Code.UNEXPECTED.error()

        if (currentStep.validationCode.lowercase() != codeLog.lowercase()) {
            throw CTDomainError.Code.CACHE_INVALID_LOG_CODE.error()
        }
        val newProgress = finishStep(userProgress, cache)
        userProgressRepository.saveCacheUserProgress(userProgress = newProgress)

        runCatchSuspendResult {
            if (newProgress.foundDate != null) {
                updateExplorationPointsUseCase(myExplorerId)
                updateCartographyPointsUseCase(
                    explorerId = cache.creatorId,
                    cacheIds = listOf(cacheId),
                )
                if (!cache.discovered) {
                    val newCache = cache.copy(discovered = true)
                    cacheRepository.saveCache(newCache)
                }
            }
            CTSuspendResult.Success(Unit)
        }
        CTSuspendResult.Success(newProgress)
    }

    private fun finishStep(userProgress: CacheUserProgress, cache: Cache): CacheUserProgress {
        val stepDone = userProgress.stepDoneRefs + userProgress.currentStepRef!!
        val allStep = getAllMyStepUseCase(cache, userProgress)
        val userProgressUpdated = userProgress.copy(
            stepDoneRefs = stepDone,
            currentStepRef = allStep.firstOrNull { !stepDone.contains(it) } ?: cache.finalStepRef,
        )
        return if (stepDone.contains(cache.finalStepRef)) {
            userProgressUpdated.copy(
                ptsWin = calculateCachePtsWinUseCase(cache = cache, userProgress = userProgress),
                foundDate = Date(),
            )
        } else {
            userProgressUpdated
        }
    }
}
