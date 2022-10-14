package com.benoitmanhes.remote_datasource.firestore.model

import com.benoitmanhes.domain.interfaces.Model

abstract class FirestoreModel<M : Model> {

    abstract fun toAppModel(id: String): M
    abstract fun toHashMap(): HashMap<String, Any>
}