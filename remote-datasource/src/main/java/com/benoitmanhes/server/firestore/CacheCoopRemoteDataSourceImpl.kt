package com.benoitmanhes.server.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.CacheCoopRemoteDataSource
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.server.firestore.model.FSCacheCoop
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CacheCoopRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : CacheCoopRemoteDataSource, FSRemoteDataSource<Cache.Coop, FSCacheCoop> {

    override val collectionRef: String = "caches-coop"
    override val fsClass: Class<FSCacheCoop> = FSCacheCoop::class.java

    override suspend fun getAllCaches(): List<Cache.Coop> = firestore.getAllFSObject()

    override suspend fun saveCache(cache: Cache.Coop): Unit = firestore.saveFSObject(
        id = cache.cacheId,
        fsObject = cache,
    )
}