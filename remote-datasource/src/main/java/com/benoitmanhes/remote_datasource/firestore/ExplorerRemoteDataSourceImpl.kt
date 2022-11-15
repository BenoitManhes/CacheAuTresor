package com.benoitmanhes.remote_datasource.firestore

import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult
import com.benoitmanhes.remote_datasource.extensions.convertToAppModel
import com.benoitmanhes.remote_datasource.extensions.listenToFlow
import com.benoitmanhes.remote_datasource.extensions.withCoroutine
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

    override suspend fun saveExplorer(explorer: Explorer) {
        firestore.collection(EXPLORER_COLLECTION)
            .document(explorer.explorerId)
            .set(
                FSExplorer(explorer).toHashMap(),
            ).withCoroutine()
    }

    override suspend fun isExplorerNameAvailable(name: String): Boolean {
        val documents = firestore.collection(EXPLORER_COLLECTION)
            .whereEqualTo(FSExplorer::name.name, name)
            .get()
            .withCoroutine()
        return documents.isEmpty
    }

    override fun getExplorerFlow(explorerId: String): Flow<Explorer> =
        firestore.collection(EXPLORER_COLLECTION)
            .document(explorerId)
            .listenToFlow<Explorer, FSExplorer>(documentId = explorerId)

    override suspend fun getExplorer(explorerId: String): Explorer =
        firestore.collection(EXPLORER_COLLECTION)
            .document(explorerId)
            .get()
            .withCoroutine()
            .convertToAppModel<Explorer, FSExplorer>(explorerId)

    override suspend fun deleteExplorer(explorerId: String) {
        firestore.collection(EXPLORER_COLLECTION)
            .document(explorerId)
            .delete()
            .withCoroutine()
    }
}
