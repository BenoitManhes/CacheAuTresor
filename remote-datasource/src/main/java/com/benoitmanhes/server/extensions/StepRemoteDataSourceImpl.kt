package com.benoitmanhes.server.extensions

import com.benoitmanhes.domain.interfaces.remotedatasource.StepRemoteDataSource
import com.benoitmanhes.domain.model.CacheStep
import com.benoitmanhes.server.firestore.FSRemoteDataSource
import com.benoitmanhes.server.firestore.model.FSCacheStep
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class StepRemoteDataSourceImpl @Inject constructor(
    firestore: FirebaseFirestore,
) : FSRemoteDataSource<CacheStep, FSCacheStep>(firestore),
    StepRemoteDataSource {

    override val collectionRef: String = "cache-step"

    override fun DocumentSnapshot.getFsClass(): Class<FSCacheStep> = FSCacheStep::class.java

    override fun CacheStep.parseToFSModel(): FSCacheStep = FSCacheStep(this)

    override suspend fun getStep(stepId: String): CacheStep? =
        getFSObject(stepId)
}