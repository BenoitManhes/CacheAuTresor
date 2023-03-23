package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.domain.interfaces.Model

interface FirestoreModel<M : Model> {

    val id: String?

    fun toAppModel(): M

    fun <T> T?.requiredField(): T =
        this ?: throw CTRemoteError.ParsingFailed(
            "Failed to parse ${this@FirestoreModel::class.java.simpleName} into Model"
        )
}
