package com.benoitmanhes.server.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.CacheRemoteDataSource
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.server.firestore.model.FSCache
import com.benoitmanhes.server.firestore.model.FSCacheClassical
import com.benoitmanhes.server.firestore.model.FSCacheCoop
import com.benoitmanhes.server.firestore.model.FSCacheMystery
import com.benoitmanhes.server.firestore.model.FSCachePiste
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CacheRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : FSRemoteDataSource<Cache, FSCache<Cache>>(firestore), CacheRemoteDataSource {
    companion object {
        private const val CLASSICAL_ID_PREFIX: String = "CL"
        private const val COOP_ID_PREFIX: String = "CO"
        private const val MYSTERY_ID_PREFIX: String = "MY"
        private const val PISTE_ID_PREFIX: String = "PI"
    }

    override val collectionRef: String = "caches"

    @Suppress("UNCHECKED_CAST")
    override fun DocumentSnapshot.getFsClass(): Class<FSCache<Cache>>? = when {
        this.id.startsWith(CLASSICAL_ID_PREFIX) -> FSCacheClassical::class.java as Class<FSCache<Cache>>
        this.id.startsWith(PISTE_ID_PREFIX) -> FSCachePiste::class.java as Class<FSCache<Cache>>
        this.id.startsWith(MYSTERY_ID_PREFIX) -> FSCacheMystery::class.java as Class<FSCache<Cache>>
        this.id.startsWith(COOP_ID_PREFIX) -> FSCacheCoop::class.java as Class<FSCache<Cache>>
        else -> null
    }

    override suspend fun getAllCaches(): List<Cache> =
        getAllFSObject()

    override suspend fun getCache(cacheId: String): Cache? =
        getFSObject(cacheId)

    override suspend fun saveCache(cache: Cache) =
        saveFSObject(id = cache.cacheId, model = cache)

    @Suppress("UNCHECKED_CAST")
    override fun Cache.parseToFSModel(): FSCache<Cache> = when (this) {
        is Cache.Classical -> FSCacheClassical(this) as FSCache<Cache>
        is Cache.Coop -> FSCacheCoop(this) as FSCache<Cache>
        is Cache.Mystery -> FSCacheMystery(this) as FSCache<Cache>
        is Cache.Piste -> FSCachePiste(this) as FSCache<Cache>
    }
}
