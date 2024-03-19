package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheStep
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.model.CacheUserStatus
import com.benoitmanhes.domain.uimodel.UIStep
import javax.inject.Inject

class GetUIStepsUseCase @Inject constructor(
    private val stepRepository: StepRepository,
) {
    suspend operator fun invoke(
        stepId: String,
        cache: Cache,
        userProgress: CacheUserProgress?,
        cacheUserStatus: CacheUserStatus,
    ): UIStep {
        val step = stepRepository.getStep(stepId)
            ?: throw CTDomainError.Code.CACHE_STEP_NOT_FOUND.error(message = "Cache step $stepId not found")

        return UIStep(
            stepId = step.stepId,
            clue = step.clue,
            showClue = (userProgress?.clueUnlockedStepRef?.contains(stepId) ?: false) || cacheUserStatus == CacheUserStatus.Owned,
            instructions = step.instruction,
            status = when {
                cacheUserStatus == CacheUserStatus.Owned -> UIStep.Status.Current
                userProgress == null -> UIStep.Status.Lock
                userProgress.stepDoneRefs.contains(stepId) -> UIStep.Status.Done
                userProgress.currentStepRef == stepId -> UIStep.Status.Current
                else -> UIStep.Status.Lock
            },
            coordinates = step.coordinates,
            type = getUIStepType(cache, step),
            code = step.validationCode,
        )
    }

    private fun getUIStepType(cache: Cache, step: CacheStep): UIStep.Type =
        if (cache.type !is Cache.Type.Classical && cache.finalStepRef == step.stepId) {
            UIStep.Type.Final
        } else {
            when (cache.type) {
                is Cache.Type.Classical -> UIStep.Type.Classical
                is Cache.Type.Mystery -> UIStep.Type.Mystery
                is Cache.Type.Piste -> UIStep.Type.Piste(
                    index = cache.type.intermediateStepIds.indexOf(step.stepId),
                )

                is Cache.Type.Coop -> {
                    val crewRef = cache.type.crewStepsMap.filterValues { list ->
                        list.contains(step.stepId)
                    }.keys.first()

                    UIStep.Type.Coop(
                        index = cache.type.crewStepsMap[crewRef]!!.indexOf(step.stepId),
                        crewPosition = crewRef,
                    )
                }
            }
        }
}
