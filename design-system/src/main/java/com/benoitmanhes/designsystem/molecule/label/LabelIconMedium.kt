package com.benoitmanhes.designsystem.molecule.label

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.atoms.spacer.SpacerMicro
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun LabelIconMedium(
    icon: IconSpec,
    text: TextSpec,
    modifier: Modifier = Modifier,
    color: Color = CTTheme.color.primary,
) {
    Row(
        modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CTIcon(
            icon = icon,
            color = color,
            size = Dimens.IconSize.Medium,
        )
        SpacerMicro()
        CTTextView(
            text = text,
            style = CTTheme.typography.body,
        )
    }
}
