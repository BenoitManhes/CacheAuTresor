package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.CacheStep

interface StepRemoteDataSource {
    suspend fun getStep(stepId: String): CacheStep?
    suspend fun getSteps(ids: List<String>): List<CacheStep>
    suspend fun saveSteps(steps: List<CacheStep>)
}
