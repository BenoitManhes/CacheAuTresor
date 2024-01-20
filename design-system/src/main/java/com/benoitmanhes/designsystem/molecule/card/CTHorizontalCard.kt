package com.benoitmanhes.designsystem.molecule.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.atoms.CTVerticalDivider
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ComposableContent

@Composable
fun CTHorizontalCard(
    modifier: Modifier = Modifier,
    borderColor: Color = CTTheme.color.border,
    onClick: () -> Unit,
    leadingContent: ComposableContent,
    content: ComposableContent,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clip(CTTheme.shape.medium)
            .clickable(onClick = onClick)
            .background(CTTheme.color.surface)
            .border(CTTheme.stroke.medium, borderColor, CTTheme.shape.medium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingContent()

        CTVerticalDivider(
            modifier = modifier.fillMaxHeight(),
            color = borderColor,
            stroke = CTTheme.stroke.medium,
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        ) {
            content()
        }
    }
}
