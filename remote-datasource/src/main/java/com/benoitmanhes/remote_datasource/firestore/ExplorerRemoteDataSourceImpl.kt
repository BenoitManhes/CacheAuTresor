package com.benoitmanhes.remote_datasource.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult
import com.benoitmanhes.remote_datasource.firestore.model.FSExplorer
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.util.Util
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ExplorerRemoteDataSourceImpl(
    private val firestore: FirebaseFirestore,
) : ExplorerRemoteDataSource {
    companion object {
        const val EXPLORER_COLLECTION: String = "explorers"
    }

    override suspend fun saveExplorer(explorer: Explorer): BResult<Explorer> =
        suspendCoroutine { continuation ->
            firestore.collection(EXPLORER_COLLECTION)
                .document(explorer.explorerId.ifEmpty { Util.autoId() })
                .set(
                    FSExplorer(explorer.name).toHashMap(),
                )
                .addOnSuccessListener { continuation.resume(BResult.Success(explorer)) }
                .addOnFailureListener { e ->
                    continuation.resume(
                        BResult.Failure(
                            BError.RemoteObjectEditingError(cause = e)
                        )
                    )
                }
        }

    override suspend fun isExplorerNameAvailable(name: String): BResult<Unit> =
        suspendCoroutine { continuation ->
            firestore.collection(EXPLORER_COLLECTION)
                .whereEqualTo(FSExplorer::name.name, name)
                .get()
                .addOnSuccessListener { documents ->
                    val result = if (documents.isEmpty) {
                        BResult.Success(Unit)
                    } else {
                        BResult.Failure(BError.ExplorerNameTakenError)
                    }
                    continuation.resume(result)
                }
                .addOnFailureListener {
                    continuation.resume(BResult.Failure(BError.GetRemoteObjectError(cause = it)))
                }
        }

    override fun getExplorerFlow(explorerId: String): Flow<BResult<Explorer>> = callbackFlow {
        val userDocument = firestore.collection(EXPLORER_COLLECTION)
            .document(explorerId)

        userDocument
            .addSnapshotListener { snapShot, e ->
                when {
                    e != null -> {
                        trySend(
                            BResult.Failure(
                                BError.GetRemoteObjectError(
                                    cause = e,
                                    message = "Failed to listen firestore user $explorerId",
                                )
                            )
                        )
                    }
                    snapShot != null && snapShot.exists() -> {
                        Timber.d(snapShot.data.toString())
                        val result = try {
                            BResult.Success(snapShot.toObject<FSExplorer>()!!.toAppModel(explorerId))
                        } catch (e: Exception) {
                            BResult.Failure(BError.RemoteObjectParsingError(cause = e))
                        }
                        trySend(result)
                    }
                    else -> {
                        trySend(
                            BResult.Failure(BError.ObjectNotFound)
                        )
                    }
                }
            }
    }

    override suspend fun deleteExplorer(explorerId: String): BResult<Unit> =
        suspendCoroutine { continuation ->
            firestore.collection(EXPLORER_COLLECTION)
                .document(explorerId)
                .delete()
                .addOnSuccessListener { continuation.resume(BResult.Success(Unit)) }
                .addOnFailureListener { e ->
                    continuation.resume(
                        BResult.Failure(
                            BError.RemoteObjectEditingError(cause = e)
                        )
                    )
                }
        }
}
