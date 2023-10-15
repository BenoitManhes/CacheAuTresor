package com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.extensions.textSpec
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetState
import com.benoitmanhes.designsystem.atoms.CTDivider
import com.benoitmanhes.designsystem.atoms.spacer.SpacerMedium
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

data class StartCoopModalBottomSheet(
    val memberPositions: Set<String>,
    val onClickMemberPosition: (String) -> Unit,
    override val onDismiss: () -> Unit = {},
) : ModalBottomSheetState {

    @Composable
    override fun Content(
        scope: ColumnScope,
        hide: () -> Unit,
    ): Unit = with(scope) {
        CTTextView(
            text = TextSpec.Resources(R.string.startCoopModal_message),
            style = CTTheme.typography.body,
            modifier = Modifier.padding(horizontal = CTTheme.spacing.large)
        )
        SpacerMedium()
        CTDivider()
        SpacerMedium()
        memberPositions.map { position ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClickMemberPosition(position)
                        hide()
                    }
                    .padding(CTTheme.spacing.large),
            ) {
                CTTextView(
                    text = position.textSpec(),
                    style = CTTheme.typography.header1,
                )
            }
        }
        SpacerMedium()
    }
}
