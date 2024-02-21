package com.benoitmanhes.designsystem.molecule.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.ProfileFilled
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.extensions.ctClickable

@Composable
fun CTRow(
    state: CTRowState,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = CTTheme.spacing.large,
        vertical = CTTheme.spacing.small,
    ),
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .ctClickable(state.onClick)
            .padding(paddingValues),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
    ) {
        state.leadingIcon?.let { icon ->
            CTIcon(icon = icon(), size = Dimens.IconSize.Medium)
        }
        state.text?.let { text ->
            CTTextView(text = text, style = CTTheme.typography.body)
        }
    }
}

object CTRow {
    private const val contentType: String = "CTRow"

    fun item(
        scope: LazyListScope,
        state: CTRowState,
        key: Any?,
        modifier: Modifier = Modifier,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            CTRow(
                state = state,
                modifier = modifier,
            )
        }
    }
}

@Stable
data class CTRowState(
    val leadingIcon: ComposeProvider<IconSpec>? = null,
    val text: TextSpec? = null,
    val onClick: (() -> Unit)? = null,
)

@Preview
@Composable
fun PreviewCTRow() {
    CTTheme {
        CTRow(
            state = CTRowState(
                leadingIcon = CTTheme.composed { icon.ProfileFilled },
                text = TextSpec.loreumIpsum(4),
            )
        )
    }
}
