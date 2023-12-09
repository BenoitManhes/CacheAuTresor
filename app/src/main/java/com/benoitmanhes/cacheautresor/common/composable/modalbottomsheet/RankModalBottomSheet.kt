package com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.common.composable.row.RankRowState
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetOption
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetState
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.button.iconbutton.CTIconButton
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

data class RankModalBottomSheet(
    val title: TextSpec,
    val explorerRanks: List<RankRowState>,
    override val onDismiss: () -> Unit = {},
) : ModalBottomSheetState {

    override val option: Set<ModalBottomSheetOption> = setOf(
        ModalBottomSheetOption.Lock,
    )

    @Composable
    override fun Content(scope: ColumnScope, hide: () -> Unit) {
        SpacerLarge()
        Row(
            modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CTTextView(
                text = title,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = CTTheme.spacing.large),
                style = CTTheme.typography.header0,
            )
            CTIconButton(
                icon = Icons.Rounded.Close.toIconSpec(),
                size = Dimens.IconButtonSize.Medium,
                onClick = hide,
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = CTTheme.spacing.large,
                horizontal = CTTheme.spacing.small,
            ),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.extraSmall),
            modifier = Modifier.fillMaxHeight(),
        ) {
            explorerRanks.forEachIndexed { index, rankRowState ->
                rankRowState.item(this, key = rankRowState.explorerName.hashCode())
            }
        }
    }
}
