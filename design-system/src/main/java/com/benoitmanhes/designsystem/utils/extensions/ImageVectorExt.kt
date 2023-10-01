package com.benoitmanhes.designsystem.utils.extensions

import androidx.compose.ui.graphics.vector.ImageVector
import com.benoitmanhes.designsystem.utils.IconSpec

fun ImageVector.toIconSpec(contentDescription: String? = null): IconSpec =
    IconSpec.VectorIcon(this, contentDescription)
