package com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetOption
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetState
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.modalBottomSheet.CTModalBottomSheetContent
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.ComposableContent
import com.benoitmanhes.designsystem.utils.IconSpec

@Stable
data class ClassicModalBottomSheet(
    val icon: ComposeProvider<IconSpec>? = null,
    val title: TextSpec? = null,
    val message: TextSpec? = null,
    val cancelAction: PrimaryButtonState? = null,
    val confirmAction: PrimaryButtonState? = null,
    val color: ComposeProvider<Color> = CTTheme.composed { this.color.primary },
    override val option: Set<ModalBottomSheetOption> = emptySet(),
    override val onDismiss: () -> Unit = {},
    val content: ComposableContent? = null,
) : ModalBottomSheetState {

    @Composable
    override fun Content(
        scope: ColumnScope,
        hide: () -> Unit,
    ): Unit = with(scope) {
        CTModalBottomSheetContent(
            hide = hide,
            icon = icon?.invoke(),
            title = title,
            message = message,
            cancelAction = cancelAction,
            confirmAction = confirmAction,
            color = color(),
            content = content,
        )
    }
}
