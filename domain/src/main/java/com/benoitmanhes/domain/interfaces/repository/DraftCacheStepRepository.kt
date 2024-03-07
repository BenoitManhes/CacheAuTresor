package com.benoitmanhes.domain.interfaces.repository

import com.benoitmanhes.domain.model.DraftCacheStep
import kotlinx.coroutines.flow.Flow

interface DraftCacheStepRepository {
    suspend fun saveDraftCacheStep(draftCacheStep: DraftCacheStep)
    suspend fun saveDraftCacheSteps(draftCacheSteps: List<DraftCacheStep>)
    suspend fun deleteDraftCacheStep(dratCacheStepId: String)
    suspend fun deleteDraftCacheSteps(draftCacheSteps: List<String>)
    suspend fun deleteAll()
    suspend fun getDraftCacheStep(dratCacheStepId: String): DraftCacheStep?
    suspend fun getDraftCacheSteps(stepsIds: List<String>): List<DraftCacheStep>
    fun getDraftCacheStepFlow(dratCacheStepId: String): Flow<DraftCacheStep?>
    fun getDraftCacheStepsFlow(dratCacheStepIds: List<String>): Flow<List<DraftCacheStep>>
}
