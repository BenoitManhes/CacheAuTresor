package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.domain.interfaces.Model

interface FirestoreModel<M : Model> {

    fun toAppModel(id: String): M
    fun toHashMap(): HashMap<String, Any>
}
