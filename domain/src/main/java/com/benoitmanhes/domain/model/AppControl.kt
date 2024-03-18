package com.benoitmanhes.domain.model

import com.benoitmanhes.common.kotlin.TextTranslatable
import com.benoitmanhes.domain.interfaces.Model

data class AppControl(
    val forceToUpgradeMinVersion: Int,
    val forceToUpgradeMessage: TextTranslatable,
    val maintenanceActive: Boolean,
    val maintenanceMessage: TextTranslatable,
) : Model
