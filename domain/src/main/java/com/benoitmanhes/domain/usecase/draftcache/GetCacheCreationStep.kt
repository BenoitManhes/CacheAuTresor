package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.domain.model.CacheCreationStep
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.domain.uimodel.UIDraftCache
import javax.inject.Inject

class GetCacheCreationStep @Inject constructor() {

    operator fun invoke(draftCache: DraftCache, steps: UIDraftCache.Steps?): CacheCreationStep {
        val stepValidity = steps?.checkValidity()
        return when {
            draftCache.title == null -> CacheCreationStep.Name
            draftCache.type == null -> CacheCreationStep.Type
            draftCache.coordinates == null -> CacheCreationStep.InitCoordinates
            stepValidity != null && stepValidity !is CacheCreationStep.StepFinal -> stepValidity
            steps is UIDraftCache.Steps.Coop && steps.crewSteps.count() < 2 -> CacheCreationStep.CoopAddCrewMember
            stepValidity != null -> stepValidity
            draftCache.difficulty == null -> CacheCreationStep.Difficulty
            draftCache.ground == null -> CacheCreationStep.Ground
            draftCache.size == null -> CacheCreationStep.Size
            draftCache.description == null -> CacheCreationStep.Description
            draftCache.lockDescription == null -> CacheCreationStep.UnlockInstruction
            draftCache.lockCode == null -> CacheCreationStep.UnlockCode
            else -> CacheCreationStep.Ready
        }
    }

    fun UIDraftCache.Steps.checkValidity(): CacheCreationStep? =
        when (this) {
            is UIDraftCache.Steps.Classical -> CacheCreationStep.StepClassical(instruction.stepDraftId).takeIf {
                instruction.isInvalid()
            }
            is UIDraftCache.Steps.Piste -> {
                val incompleteStep = intermediarySteps.mapIndexed { index, draftCacheStep ->
                    CacheCreationStep.StepPiste(
                        stepRef = draftCacheStep.stepDraftId,
                        index = index
                    ).takeIf { draftCacheStep.isInvalid() }
                }
                    .filterNotNull()
                    .firstOrNull()
                val finalStep = CacheCreationStep.StepFinal(finalStep.stepDraftId).takeIf { finalStep.isInvalid() }
                incompleteStep ?: finalStep
            }

            is UIDraftCache.Steps.Mystery -> {
                CacheCreationStep.StepMystery(enigma.stepDraftId).takeIf { enigma.isInvalid() }
                    ?: CacheCreationStep.StepFinal(finalStep.stepDraftId).takeIf { finalStep.isInvalid() }
            }

            is UIDraftCache.Steps.Coop -> {
                val stepIncomplete = crewSteps.firstNotNullOfOrNull { (crewMemberRef, steps) ->
                    steps.mapIndexed { index, draftCacheStep ->
                        CacheCreationStep.StepCoop(
                            stepRef = draftCacheStep.stepDraftId,
                            index = index,
                            crewMemberRef = crewMemberRef,
                        ).takeIf { draftCacheStep.isInvalid() }
                    }
                        .filterNotNull()
                        .firstOrNull()
                }
                val finalStep = CacheCreationStep.StepFinal(finalStep.stepDraftId).takeIf { finalStep.isInvalid() }

                stepIncomplete ?: finalStep
            }
        }

    private fun DraftCacheStep.isInvalid(): Boolean =
        this.instruction == null
            || this.coordinates == null
            || this.validationCode == null
}
