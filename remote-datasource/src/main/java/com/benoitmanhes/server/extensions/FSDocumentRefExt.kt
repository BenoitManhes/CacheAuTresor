package com.benoitmanhes.server.extensions

import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.domain.interfaces.Model
import com.benoitmanhes.server.firestore.model.FirestoreModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

inline fun <M : Model, reified F : FirestoreModel<M>> DocumentReference.listenToFlow(
    noinline onFailure: (Exception) -> Throwable = { it.toCTError() },
    noinline onSuccess: ProducerScope<M>.(DocumentSnapshot) -> Unit = { snapshot -> snapshot.convertToAppModel<M, F>() },
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
                throw CTRemoteError.ObjectNotFound(message = "${F::class.simpleName} not found")
            }
        }
    }
}

inline fun <M : Model, reified F : FirestoreModel<M>> DocumentSnapshot.convertToAppModel(): M {
    val fsModel = this.toObject<F>()
        ?: throw CTRemoteError.ParsingFailed(message = "Failed to parse snapshot to ${F::class.simpleName}, with id ${this.id}")
    return fsModel.toAppModel(this.id)
}
