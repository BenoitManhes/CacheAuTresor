package com.benoitmanhes.designsystem.molecule.bottomnavbar

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun BottomBarFloatingButton(
    icon: IconSpec,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = CTTheme.color.onPrimary,
    backgroundColor: Color = CTTheme.color.primary,
) {
    FloatingActionButton(
        modifier = modifier,
        shape = CTTheme.shape.circle,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = CTTheme.elevation.small),
        onClick = onClick,
        backgroundColor = backgroundColor,
    ) {
        CTIcon(
            icon = icon,
            size = Dimens.IconSize.Medium,
            color = contentColor,
        )
    }
}
