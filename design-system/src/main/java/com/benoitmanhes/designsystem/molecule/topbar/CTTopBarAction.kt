package com.benoitmanhes.designsystem.molecule.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

interface CTTopBarAction {
    val icon: IconSpec
    val onClick: () -> Unit

    data class Save(override val onClick: () -> Unit) : CTTopBarAction {
        override val icon: IconSpec = Icons.Rounded.Check.toIconSpec()
    }
}
