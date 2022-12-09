package com.benoitmanhes.cacheautresor.common.composable.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBar
import com.benoitmanhes.designsystem.molecule.topbar.TopBarOption
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun DefaultTopBar(
    title: TextSpec,
    onNavBack: () -> Unit,
    modifier: Modifier = Modifier,
    options: Set<TopBarOption> = emptySet(),
) {
    CTTopBar(
        title = title,
        modifier = modifier,
        options = buildSet {
            add(
                TopBarOption.NavIcon(
                    icon = IconSpec.VectorIcon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                    ),
                    onClick = onNavBack,
                )
            )
            addAll(options)
        }
    )
}
