package com.benoitmanhes.designsystem.molecule.sticker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.common.compose.text.TextSpec

@Composable
fun CTSticker(
    label: TextSpec,
    modifier: Modifier = Modifier,
    surfaceColor: Color = CTTheme.color.primarySurface,
    textColor: Color = CTTheme.color.primary,
) {
    Box(
        modifier = modifier
            .clip(CTTheme.shape.circle)
            .background(surfaceColor)
            .padding(vertical = CTTheme.spacing.extraSmall, horizontal = CTTheme.spacing.small),
    ) {
        CTTextView(
            text = label,
            color = textColor,
            style = CTTheme.typography.bodySmallBold,
        )
    }
}
