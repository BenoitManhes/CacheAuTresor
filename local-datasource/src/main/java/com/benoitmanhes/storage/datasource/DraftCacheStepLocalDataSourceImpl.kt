package com.benoitmanhes.storage.datasource

import com.benoitmanhes.domain.interfaces.localdatasource.DraftCacheStepLocalDataSource
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.storage.dao.DraftCacheStepDao
import com.benoitmanhes.storage.model.RoomDraftCacheStep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DraftCacheStepLocalDataSourceImpl @Inject constructor(
    private val draftCacheStepDao: DraftCacheStepDao,
) : DraftCacheStepLocalDataSource {
    override suspend fun saveDraftCacheStep(draftCacheStep: DraftCacheStep): Unit = withContext(Dispatchers.IO) {
        draftCacheStepDao.insert(RoomDraftCacheStep.build(draftCacheStep))
    }

    override suspend fun saveDraftCacheSteps(draftCacheSteps: List<DraftCacheStep>): Unit = withContext(
        Dispatchers.IO
    ) {
        draftCacheStepDao.insert(draftCacheSteps.map(RoomDraftCacheStep::build))
    }

    override suspend fun deleteDraftCacheStep(dratCacheStepId: String): Unit = withContext(Dispatchers.IO) {
        draftCacheStepDao.delete(dratCacheStepId)
    }

    override suspend fun deleteDraftCacheSteps(draftCacheSteps: List<String>): Unit = withContext(Dispatchers.IO) {
        draftCacheStepDao.delete(draftCacheSteps)
    }

    override suspend fun deleteAll(): Unit = withContext(Dispatchers.IO) {
        draftCacheStepDao.deleteAll()
    }

    override suspend fun getDraftCacheStep(dratCacheStepId: String): DraftCacheStep? = withContext(Dispatchers.IO) {
        draftCacheStepDao.findWithId(dratCacheStepId)?.toAppModel()
    }

    override fun getDraftCacheStepFlow(dratCacheStepId: String): Flow<DraftCacheStep?> =
        draftCacheStepDao.findWithIdFlow(dratCacheStepId).map { it?.toAppModel() }

    override fun getDraftCacheStepsFlow(dratCacheStepIds: List<String>): Flow<List<DraftCacheStep>> =
        draftCacheStepDao.findWithIdsFlow(dratCacheStepIds).map { list ->
            list.map { it.toAppModel() }
        }
}
