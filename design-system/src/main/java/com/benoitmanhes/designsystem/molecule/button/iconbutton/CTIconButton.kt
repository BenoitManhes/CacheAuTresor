package com.benoitmanhes.designsystem.molecule.button.iconbutton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CTIconButton(
    icon: IconSpec,
    size: Dimens.IconButtonSize,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier.size(size.button),
        shape = CTTheme.shape.circle,
        color = CTTheme.color.surface,
        elevation = CTTheme.elevation.none,
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CTIcon(
                icon = icon,
                size = size.icon,
            )
        }
    }
}
