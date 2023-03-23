package com.benoitmanhes.server.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.CacheClassicalRemoteDataSource
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.server.firestore.model.FSCacheClassical
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CacheClassicalRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : CacheClassicalRemoteDataSource, FSRemoteDataSource<Cache.Classical, FSCacheClassical> {

    override val collectionRef: String = "caches-classical"
    override val fsClass: Class<FSCacheClassical> = FSCacheClassical::class.java

    override suspend fun getAllCaches(): List<Cache.Classical> = firestore.getAllFSObject()

    override suspend fun saveCache(cache: Cache.Classical) = firestore.saveFSObject(
        id = cache.cacheId,
        fsObject = cache,
    )
}
