package com.benoitmanhes.server.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.CacheUserProgressRemoteDataSource
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.server.extensions.withCoroutine
import com.benoitmanhes.server.firestore.model.FSCacheUserProgress
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CacheUserProgressRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : FSRemoteDataSource<CacheUserProgress, FSCacheUserProgress>(firestore),
    CacheUserProgressRemoteDataSource {

    override val collectionRef: String = "cache-user-progress"

    override fun DocumentSnapshot.getFsClass(): Class<FSCacheUserProgress> = FSCacheUserProgress::class.java

    override fun CacheUserProgress.parseToFSModel(): FSCacheUserProgress = FSCacheUserProgress(this)

    override suspend fun getCacheUserProgress(explorerId: String, cacheId: String): CacheUserProgress? =
        firestore.collection(collectionRef)
            .whereEqualTo(FSCacheUserProgress::explorerId.name, explorerId)
            .whereEqualTo(FSCacheUserProgress::cacheId.name, cacheId)
            .get()
            .withCoroutine()
            .firstOrNull()
            ?.convertToAppModel()

    override suspend fun saveCacheUserProgress(userProgress: CacheUserProgress): Unit =
        saveFSObject(userProgress.id, userProgress)
}
