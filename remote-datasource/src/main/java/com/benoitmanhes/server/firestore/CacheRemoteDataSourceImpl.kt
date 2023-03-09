package com.benoitmanhes.server.firestore

import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.domain.interfaces.remotedatasource.CacheRemoteDataSource
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.server.extensions.withCoroutine
import com.benoitmanhes.server.firestore.model.FSCacheClassical
import com.benoitmanhes.server.firestore.model.FSCacheCoop
import com.benoitmanhes.server.firestore.model.FSCacheMystery
import com.benoitmanhes.server.firestore.model.FSCachePiste
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber
import javax.inject.Inject

class CacheRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : CacheRemoteDataSource {
    companion object {
        const val CACHE_COLLECTION: String = "caches"
        private const val CLASSICAL_ID_PREFIX: String = "CL"
        private const val PISTE_ID_PREFIX: String = "PI"
        private const val MYSTERY_ID_PREFIX: String = "MY"
        private const val COOP_ID_PREFIX: String = "CO"
    }

    override suspend fun getAllCaches(): List<Cache> =
        firestore.collection(CACHE_COLLECTION)
            .get()
            .withCoroutine()
            .mapNotNull { document ->
                document.convertToCacheModel(document.id)
            }

    override suspend fun saveCache(cache: Cache) {
        val fsModel = when (cache) {
            is Cache.Classical -> FSCacheClassical(cache)
            is Cache.Coop -> FSCacheCoop(cache)
            is Cache.Mystery -> FSCacheMystery(cache)
            is Cache.Piste -> FSCachePiste(cache)
        }
        firestore.collection(CACHE_COLLECTION)
            .document(cache.cacheId)
            .set(fsModel)
            .withCoroutine()
    }

    private fun DocumentSnapshot.convertToCacheModel(
        documentId: String,
    ): Cache? {
        val fsClass = when {
            documentId.startsWith(CLASSICAL_ID_PREFIX) -> FSCacheClassical::class.java
            documentId.startsWith(PISTE_ID_PREFIX) -> FSCachePiste::class.java
            documentId.startsWith(MYSTERY_ID_PREFIX) -> FSCacheMystery::class.java
            documentId.startsWith(COOP_ID_PREFIX) -> FSCacheCoop::class.java
            else -> null
        }
        return fsClass?.let {
            this.toObject(fsClass)?.toAppModel(documentId)
        } ?: kotlin.run {
            Timber.e(CTRemoteError.ParsingFailed(message = "Failed to parse snapshot to Cache, with id $documentId"))
            null
        }
    }
}