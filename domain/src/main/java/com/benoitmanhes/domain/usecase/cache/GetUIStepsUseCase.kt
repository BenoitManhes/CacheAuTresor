package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.uimodel.UIStep
import javax.inject.Inject

class GetUIStepsUseCase @Inject constructor(
    private val stepRepository: StepRepository,
) {
    suspend operator fun invoke(stepId: String, userProgress: CacheUserProgress?): UIStep {
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
        )
    }
}
