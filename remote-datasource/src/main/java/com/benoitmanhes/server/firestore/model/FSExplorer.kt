package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.domain.model.Explorer
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class FSExplorer(
    val name: String? = null,
) : FirestoreModel<Explorer>() {

    constructor(explorer: Explorer) : this(
        name = explorer.name,
    )

    override fun toAppModel(id: String): Explorer = Explorer(
        explorerId = id,
        name = name.requiredField(),
    )

    fun toHashMap(): HashMap<String, Any> = hashMapOf(
        FSExplorer::name.name to this.name!!,
    )
}
