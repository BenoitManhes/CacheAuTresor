package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.common.kotlin.TextTranslatable
import com.benoitmanhes.domain.model.AppControl
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class FSAppControl(
    val forceToUpgradeMessage: Map<String, String>? = null,
    val versionCodeMin: Int? = null,
    val maintenanceActive: Boolean? = null,
    val maintenanceMessage: Map<String, String>? = null,
) : FirestoreModel<AppControl> {
    override val id: String = ""

    override fun toAppModel(): AppControl = AppControl(
        forceToUpgradeMinVersion = versionCodeMin.requiredField(),
        forceToUpgradeMessage = TextTranslatable(forceToUpgradeMessage ?: emptyMap()),
        maintenanceActive = maintenanceActive.requiredField(),
        maintenanceMessage = TextTranslatable(maintenanceMessage ?: emptyMap()),
    )
}
