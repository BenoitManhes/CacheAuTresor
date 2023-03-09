package com.benoitmanhes.server.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.server.extensions.convertToAppModel
import com.benoitmanhes.server.extensions.listenToFlow
import com.benoitmanhes.server.extensions.withCoroutine
import com.benoitmanhes.server.firestore.model.FSExplorer
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

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
            .listenToFlow<Explorer, FSExplorer>()

    override suspend fun getExplorer(explorerId: String): Explorer =
        firestore.collection(EXPLORER_COLLECTION)
            .document(explorerId)
            .get()
            .withCoroutine()
            .convertToAppModel<Explorer, FSExplorer>()

    override suspend fun deleteExplorer(explorerId: String) {
        firestore.collection(EXPLORER_COLLECTION)
            .document(explorerId)
            .delete()
            .withCoroutine()
    }
}
