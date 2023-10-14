package com.benoitmanhes.designsystem.molecule.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import com.benoitmanhes.designsystem.utils.IconSpec

sealed interface CTNavAction {
    val icon: IconSpec
    val onClick: () -> Unit

    data class Back(override val onClick: () -> Unit) : CTNavAction {
        override val icon: IconSpec = IconSpec.VectorIcon(Icons.Rounded.ArrowBack, null)
    }

    data class Close(override val onClick: () -> Unit) : CTNavAction {
        override val icon: IconSpec = IconSpec.VectorIcon(Icons.Rounded.Close, null)
    }
}
