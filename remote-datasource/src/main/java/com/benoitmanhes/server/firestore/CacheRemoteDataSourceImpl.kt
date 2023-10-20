package com.benoitmanhes.server.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.CacheRemoteDataSource
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.server.firestore.model.FSCache
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CacheRemoteDataSourceImpl @Inject constructor(
    val firestore: FirebaseFirestore,
) : FSRemoteDataSource<Cache, FSCache>(firestore), CacheRemoteDataSource {

    override val collectionRef: String = "caches"

    override fun DocumentSnapshot.getFsClass(): Class<FSCache> = FSCache::class.java
    override fun Cache.parseToFSModel(): FSCache = FSCache(this)

    override suspend fun getAllCaches(): List<Cache> =
        getAllFSObject()

    override suspend fun getCache(cacheId: String): Cache? =
        getFSObject(cacheId)

    override suspend fun saveCache(cache: Cache) =
        saveFSObject(id = cache.cacheId, model = cache)
}
