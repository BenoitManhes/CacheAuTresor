package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.domain.interfaces.Model

abstract class FirestoreModel<M : Model> {

    abstract fun toAppModel(id: String): M

    protected fun <T> T?.requiredField(): T =
        this ?: throw CTRemoteError.ParsingFailed("Failed to parse ${this@FirestoreModel::class.java.simpleName} into Model")
}
