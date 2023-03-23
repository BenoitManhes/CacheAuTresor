package com.benoitmanhes.server.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.CachePisteRemoteDataSource
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.server.firestore.model.FSCachePiste
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CachePisteRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : CachePisteRemoteDataSource, FSRemoteDataSource<Cache.Piste, FSCachePiste> {

    override val collectionRef: String = "caches-piste"
    override val fsClass: Class<FSCachePiste> = FSCachePiste::class.java

    override suspend fun getAllCaches(): List<Cache.Piste> = firestore.getAllFSObject()

    override suspend fun saveCache(cache: Cache.Piste): Unit = firestore.saveFSObject(
        id = cache.cacheId,
        fsObject = cache,
    )
}