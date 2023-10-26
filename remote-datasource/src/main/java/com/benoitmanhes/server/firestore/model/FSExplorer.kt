package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.domain.model.Explorer
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class FSExplorer(
    override val id: String? = null,
    val name: String? = null,
    val cachesMap: Map<String, Int>? = null,
    val explorationPts: Int? = null,
) : FirestoreModel<Explorer> {

    constructor(explorer: Explorer) : this(
        id = explorer.explorerId,
        name = explorer.name,
        cachesMap = explorer.cachesMap,
        explorationPts = explorer.explorationPts,
    )

    override fun toAppModel(): Explorer = Explorer(
        explorerId = id.requiredField(),
        name = name.requiredField(),
        cachesMap = cachesMap ?: emptyMap(),
        explorationPts = explorationPts ?: 0,
    )
}
