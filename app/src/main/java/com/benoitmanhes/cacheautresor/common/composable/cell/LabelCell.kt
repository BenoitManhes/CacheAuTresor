package com.benoitmanhes.cacheautresor.common.composable.cell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun LabelCell(
    state: LabelCellState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.extraSmall),
    ) {
        state.leadingIcon?.let { icon ->
            CTIcon(icon = icon, size = Dimens.IconSize.Medium)
        }

        Column {
            state.headingText?.let { headingText ->
                CTTextView(text = headingText, style = CTTheme.typography.caption)
            }
            CTTextView(text = state.label, style = CTTheme.typography.body)
        }
    }
}

data class LabelCellState(
    val label: TextSpec,
    val headingText: TextSpec? = null,
    val leadingIcon: IconSpec? = null,
)

@Preview
@Composable
private fun PreviewLabelCell() {
    CTTheme {
        LabelCell(
            LabelCellState(
                label = TextSpec.loreumIpsum(4),
                headingText = TextSpec.loreumIpsum(3),
                leadingIcon = CTTheme.icon.ProfileFilled,
            )
        )
    }
}
