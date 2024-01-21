package com.benoitmanhes.repository

import com.benoitmanhes.domain.interfaces.localdatasource.DraftCacheStepLocalDataSource
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.DraftCacheStep
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DraftCacheStepRepositoryImpl @Inject constructor(
    private val draftCacheStepLocalDataSource: DraftCacheStepLocalDataSource,
) : DraftCacheStepRepository {

    override suspend fun saveDraftCacheStep(draftCacheStep: DraftCacheStep) {
        draftCacheStepLocalDataSource.saveDraftCacheStep(draftCacheStep)
    }

    override suspend fun saveDraftCacheSteps(draftCacheSteps: List<DraftCacheStep>) {
        draftCacheStepLocalDataSource.saveDraftCacheSteps(draftCacheSteps)
    }

    override suspend fun deleteDraftCacheStep(dratCacheStepId: String) {
        draftCacheStepLocalDataSource.deleteDraftCacheStep(dratCacheStepId)
    }

    override suspend fun deleteDraftCacheSteps(draftCacheSteps: List<String>) {
        draftCacheStepLocalDataSource.deleteDraftCacheSteps(draftCacheSteps)
    }

    override suspend fun deleteAll() {
        draftCacheStepLocalDataSource.deleteAll()
    }

    override suspend fun getDraftCacheStep(dratCacheStepId: String): DraftCacheStep? =
        draftCacheStepLocalDataSource.getDraftCacheStep(dratCacheStepId)

    override fun getDraftCacheStepFlow(dratCacheStepId: String): Flow<DraftCacheStep?> =
        draftCacheStepLocalDataSource.getDraftCacheStepFlow(dratCacheStepId)

    override fun getDraftCacheStepsFlow(dratCacheStepIds: List<String>): Flow<List<DraftCacheStep>> =
        draftCacheStepLocalDataSource.getDraftCacheStepsFlow(dratCacheStepIds)
}
