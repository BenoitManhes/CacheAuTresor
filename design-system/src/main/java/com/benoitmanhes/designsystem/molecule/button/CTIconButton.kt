package com.benoitmanhes.designsystem.molecule.button

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun CTIconButton(
    icon: IconSpec,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CTIcon(
        icon = icon,
        modifier = modifier
            .size(Dimens.Button.iconButtonSize)
            .clip(CTTheme.shape.circle),
        onClick = onClick,
    )

}