package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.CacheStep

interface StepRepository {
    suspend fun getStep(stepId: String): CacheStep?
    suspend fun getSteps(stepIds: List<String>): List<CacheStep>
    suspend fun saveSteps(steps: List<CacheStep>)
}
