package com.benoitmanhes.domain.interfaces.remotedatasource

import com.benoitmanhes.domain.model.CacheStep

interface StepRemoteDataSource {
    suspend fun getStep(stepId: String): CacheStep?
}
