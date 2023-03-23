package com.benoitmanhes.server.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.CacheMysteryRemoteDataSource
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.server.firestore.model.FSCacheMystery
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CacheMysteryRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : CacheMysteryRemoteDataSource, FSRemoteDataSource<Cache.Mystery, FSCacheMystery> {

    override val collectionRef: String = "caches-mystery"
    override val fsClass: Class<FSCacheMystery> = FSCacheMystery::class.java

    override suspend fun getAllCaches(): List<Cache.Mystery> = firestore.getAllFSObject()

    override suspend fun saveCache(cache: Cache.Mystery): Unit = firestore.saveFSObject(
        id = cache.cacheId,
        fsObject = cache,
    )
}
