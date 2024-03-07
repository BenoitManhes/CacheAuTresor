package com.benoitmanhes.domain.model

sealed interface CacheCreationStep {
    data object Name : CacheCreationStep
    data object Type : CacheCreationStep
    data object InitCoordinates : CacheCreationStep
    data class StepClassical(val stepRef: String) : CacheCreationStep
    data class StepMystery(val stepRef: String) : CacheCreationStep
    data class StepPiste(val stepRef: String, val index: Int) : CacheCreationStep
    data class StepCoop(val stepRef: String, val index: Int, val crewMemberRef: String) : CacheCreationStep
    data class StepFinal(val stepRef: String) : CacheCreationStep
    data object CoopAddCrewMember : CacheCreationStep
    data object Difficulty : CacheCreationStep
    data object Ground : CacheCreationStep
    data object Size : CacheCreationStep
    data object Description : CacheCreationStep
    data object UnlockInstruction : CacheCreationStep
    data object UnlockCode : CacheCreationStep
    data object Ready : CacheCreationStep
}
