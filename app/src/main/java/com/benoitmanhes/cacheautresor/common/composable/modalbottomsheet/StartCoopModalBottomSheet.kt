package com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetOption
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetState
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTDivider
import com.benoitmanhes.designsystem.atoms.spacer.SpacerMedium
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.icons.iconpack.Coop
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.extensions.ctClickable
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

data class StartCoopModalBottomSheet(
    val crewPositions: Set<String>,
    val onClickCrewPosition: (String) -> Unit,
    override val onDismiss: () -> Unit = {},
) : ModalBottomSheetState {

    override val option: Set<ModalBottomSheetOption> = emptySet()

    @Composable
    override fun Content(
        scope: ColumnScope,
        hide: () -> Unit,
    ): Unit = ClassicModalBottomSheet(
        icon = CTTheme.icon.Coop.toIconSpec(),
        title = TextSpec.Resources(R.string.cacheDetail_startCoopModal_title),
        message = TextSpec.Resources(R.string.cacheDetail_startCoopModal_message, crewPositions.count()),
        content = {
            CTDivider()
            SpacerMedium()
            crewPositions.map { position ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .ctClickable(
                            onClick = {
                                onClickCrewPosition(position)
                                hide()
                            }
                        )
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
    ).Content(scope = scope, hide = hide)
}
