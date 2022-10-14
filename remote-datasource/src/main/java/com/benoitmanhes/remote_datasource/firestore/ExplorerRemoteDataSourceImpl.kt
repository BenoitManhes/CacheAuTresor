package com.benoitmanhes.remote_datasource.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult
import com.benoitmanhes.remote_datasource.firestore.model.FSExplorer
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber

class ExplorerRemoteDataSourceImpl(
    private val firestore: FirebaseFirestore,
) : ExplorerRemoteDataSource {
    companion object {
        const val EXPLORER_COLLECTION: String = "explorers"
    }

    fun saveUser(explorer: Explorer) {
        firestore.collection(EXPLORER_COLLECTION)
            .document(explorer.explorerId)
            .set(
                FSExplorer(explorer.explorerId).toHashMap(),
            )
    }

    fun getExplorer(explorerId: String): Flow<BResult<Explorer>> = callbackFlow {

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
}
