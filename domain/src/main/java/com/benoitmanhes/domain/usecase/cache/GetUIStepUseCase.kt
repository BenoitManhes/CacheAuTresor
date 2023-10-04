package com.benoitmanhes.domain.usecase.cache

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.extensions.error
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.uimodel.UIStep
import javax.inject.Inject

class GetUIStepUseCase @Inject constructor(
    private val stepRepository: StepRepository,
) {
    suspend operator fun invoke(stepId: String, userProgress: CacheUserProgress): UIStep {
        val step = stepRepository.getStep(stepId)
            ?: throw CTDomainError.Code.CACHE_STEP_NOT_FOUND.error()

        return UIStep(
            clue = step.clue,
            showClue = userProgress.clueUnlockedStepRef.contains(stepId),
            instructions = step.instruction,
            status = when {
                userProgress.stepDoneRefs.contains(stepId) -> UIStep.Status.Done
                userProgress.currentStepRef == stepId -> UIStep.Status.Current
                else -> UIStep.Status.Lock
            },
            coordinates = step.coordinates,
        )
    }
}
