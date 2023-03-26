package com.benoitmanhes.server.firestore

import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.domain.interfaces.Model
import com.benoitmanhes.server.extensions.toCTError
import com.benoitmanhes.server.extensions.withCoroutine
import com.benoitmanhes.server.firestore.model.FirestoreModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

abstract class FSRemoteDataSource<M : Model, F : FirestoreModel<M>>(
    private val firestore: FirebaseFirestore,
) {

    protected abstract val collectionRef: String

    protected abstract fun DocumentSnapshot.getFsClass(): Class<F>?
    protected abstract fun M.parseToFSModel(): F

    protected suspend fun getFSObject(objectId: String): M? =
        firestore.collection(collectionRef)
            .document(objectId)
            .get()
            .withCoroutine()
            .convertToAppModel()

    protected suspend fun getAllFSObject(): List<M> =
        firestore.collection(collectionRef)
            .get()
            .withCoroutine()
            .mapNotNull { document ->
                document.convertToAppModel()
            }

    protected fun getFSObjectFlow(id: String): Flow<M> =
        firestore.collection(collectionRef)
            .document(id)
            .listenToFlow()

    protected suspend fun saveFSObject(id: String, model: M) {
        firestore.collection(collectionRef)
            .document(id)
            .set(model.parseToFSModel())
            .withCoroutine()
    }

    protected suspend fun deleteFSObject(id: String) {
        firestore.collection(collectionRef)
            .document(id)
            .delete()
            .withCoroutine()
    }

    protected fun DocumentReference.listenToFlow(
        onFailure: (Exception) -> Throwable = { it.toCTError() },
        onSuccess: ProducerScope<M>.(DocumentSnapshot) -> Unit = { snapshot -> snapshot.convertToAppModel() },
    ): Flow<M> = callbackFlow {
        this@listenToFlow.addSnapshotListener { snapShot, e ->
            when {
                e != null -> {
                    throw onFailure(e)
                }
                snapShot != null && snapShot.exists() -> {
                    this.onSuccess(snapShot)
                }
                else -> {
                    throw CTRemoteError.ObjectNotFound("Objet don’t exist")
                }
            }
        }
    }

    protected fun DocumentSnapshot.convertToAppModel(): M? {
        val fsClass = getFsClass() ?: throw CTRemoteError.ParsingFailed(
            message = "Failed to get fsClass, with id ${this.id}"
        )
        return this.toObject(fsClass)?.toAppModel()
    }
}
