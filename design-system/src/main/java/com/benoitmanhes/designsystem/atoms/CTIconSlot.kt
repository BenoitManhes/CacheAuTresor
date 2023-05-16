package com.benoitmanhes.designsystem.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun CTIconSlot(
    icon: IconSpec,
    size: Dimens.IconSlotSize,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor),
) {
    Box(
        modifier = Modifier
            .size(size.container)
            .clip(CTTheme.shape.circle)
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        CTIcon(
            icon = icon,
            size = size.icon,
            color = contentColor,
        )
    }
}
