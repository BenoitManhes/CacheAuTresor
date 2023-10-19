package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheStep
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.uimodel.UIStep
import javax.inject.Inject

class GetUIStepsUseCase @Inject constructor(
    private val stepRepository: StepRepository,
) {
    suspend operator fun invoke(
        stepId: String,
        cache: Cache,
        userProgress: CacheUserProgress?,
    ): UIStep {
        val step = stepRepository.getStep(stepId)
            ?: throw CTDomainError.Code.CACHE_STEP_NOT_FOUND.error(message = "Cache step $stepId not found")

        return UIStep(
            stepId = step.stepId,
            clue = step.clue,
            showClue = userProgress?.clueUnlockedStepRef?.contains(stepId) ?: false,
            instructions = step.instruction,
            status = when {
                userProgress == null -> UIStep.Status.Lock
                userProgress.stepDoneRefs.contains(stepId) -> UIStep.Status.Done
                userProgress.currentStepRef == stepId -> UIStep.Status.Current
                else -> UIStep.Status.Lock
            },
            coordinates = step.coordinates,
            type = getUIStepType(cache, step),
        )
    }

    private fun getUIStepType(cache: Cache, step: CacheStep): UIStep.Type =
        if (cache !is Cache.Classical && cache.finalStepRef == step.stepId) {
            UIStep.Type.Final
        } else {
            when (cache) {
                is Cache.Classical -> UIStep.Type.Classical
                is Cache.Mystery -> UIStep.Type.Mystery
                is Cache.Piste -> UIStep.Type.Piste(
                    index = cache.intermediaryStepRefs.indexOf(step.stepId),
                )

                is Cache.Coop -> {
                    val crewRef = cache.crewStepRefs.filterValues { list ->
                        list.contains(step.stepId)
                    }.keys.first()

                    UIStep.Type.Coop(
                        index = cache.crewStepRefs[crewRef]!!.indexOf(step.stepId),
                        crewPosition = crewRef,
                    )
                }
            }
        }
}
