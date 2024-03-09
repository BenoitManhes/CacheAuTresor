package com.benoitmanhes.repository

import com.benoitmanhes.domain.interfaces.remotedatasource.StepRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.domain.model.CacheStep
import javax.inject.Inject

class StepRepositoryImpl @Inject constructor(
    private val remoteDataSource: StepRemoteDataSource,
) : StepRepository {
    override suspend fun getStep(stepId: String): CacheStep? =
        remoteDataSource.getStep(stepId)

    override suspend fun getSteps(stepIds: List<String>): List<CacheStep> =
        remoteDataSource.getSteps(stepIds)

    override suspend fun saveSteps(steps: List<CacheStep>) {
        remoteDataSource.saveSteps(steps)
    }
}
