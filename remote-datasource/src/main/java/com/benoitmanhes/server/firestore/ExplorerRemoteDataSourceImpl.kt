package com.benoitmanhes.server.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.server.extensions.withCoroutine
import com.benoitmanhes.server.firestore.model.FSExplorer
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExplorerRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : FSRemoteDataSource<Explorer, FSExplorer>(firestore), ExplorerRemoteDataSource {

    override val collectionRef: String = "explorers"
    override fun DocumentSnapshot.getFsClass(): Class<FSExplorer> = FSExplorer::class.java

    override fun Explorer.parseToFSModel(): FSExplorer = FSExplorer(this)
    override suspend fun saveExplorer(explorer: Explorer): Unit = saveFSObject(explorer.explorerId, explorer)

    override suspend fun isExplorerNameAvailable(name: String): Boolean {
        val documents = firestore.collection(collectionRef)
            .whereEqualTo(FSExplorer::name.name, name)
            .get()
            .withCoroutine()
        return documents.isEmpty
    }

    override fun getExplorerFlow(explorerId: String): Flow<Explorer> = getFSObjectFlow(explorerId)

    override suspend fun getExplorer(explorerId: String): Explorer? = getFSObject(explorerId)

    override suspend fun getAllExplorers(): List<Explorer> = getAllFSObject()

    override suspend fun deleteExplorer(explorerId: String): Unit = deleteFSObject(explorerId)
}
