package com.benoitmanhes.designsystem.molecule.topbar

import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.typo
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTTopBar(
    title: TextSpec,
    modifier: Modifier = Modifier,
    options: Set<TopBarOption> = emptySet(),
) {
    SmallTopAppBar(
        modifier = modifier,
        title = {
            CTTextView(
                text = title,
                style = MaterialTheme.typo.header1,
            )
        },
        navigationIcon = {
            options.filterIsInstance<TopBarOption.NavIcon>().forEach {
                CTIcon(
                    icon = it.icon,
                    onClick = it.onClick,
                )
            }
        },
        actions = {
            options.filterIsInstance<TopBarOption.ActionIcon>().forEach {
                CTIcon(
                    icon = it.icon,
                    onClick = it.onClick,
                )
            }
        }
    )
}