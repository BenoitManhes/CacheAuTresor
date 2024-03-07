package com.benoitmanhes.designsystem.molecule.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

interface CTTopBarAction {
    val icon: ComposeProvider<IconSpec>
    val onClick: () -> Unit

    data class Save(override val onClick: () -> Unit) : CTTopBarAction {
        override val icon: ComposeProvider<IconSpec> = { Icons.Rounded.Check.toIconSpec() }
    }

    data class Delete(override val onClick: () -> Unit) : CTTopBarAction {
        override val icon: ComposeProvider<IconSpec> = CTTheme.composed { icon.Delete }
    }
}
