package com.benoitmanhes.designsystem.molecule.slot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun IconSlotHuge(
    icon: IconSpec,
    backgroundColor: Color,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor),
) {
    Box(
        modifier = Modifier
            .size(Dimens.Size.hugeIconSlot)
            .clip(CTTheme.shape.circle)
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        CTIcon(
            icon = icon,
            size = Dimens.Size.hugeIconSize,
            color = contentColor,
        )
    }
}