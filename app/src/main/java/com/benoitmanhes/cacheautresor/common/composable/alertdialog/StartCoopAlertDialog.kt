package com.benoitmanhes.cacheautresor.common.composable.alertdialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.alertdialog.AlertDialogState
import com.benoitmanhes.designsystem.atoms.spacer.SpacerExtraSmall
import com.benoitmanhes.designsystem.atoms.text.CTMarkdownText
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.alertdialog.AlertDialogAction
import com.benoitmanhes.designsystem.molecule.alertdialog.CTAlertDialog
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

data class StartCoopAlertDialog(
    val cacheTitle: String,
    val crewPosition: String,
    val onConfirm: () -> Unit,
) : AlertDialogState {
    override val onDismiss: (() -> Unit)? = null
    override val actions: List<AlertDialogAction> = listOf(
        CommonAlertDialogAction.no { },
        CommonAlertDialogAction.letsGo(onConfirm),
    )

    @Composable
    override fun Content(closeDialog: () -> Unit) {
        CTAlertDialog(
            title = null,
            content = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    CTMarkdownText(
                        markdown = TextSpec.Resources(
                            R.string.startCoopAlert_message,
                            cacheTitle,
                            crewPosition,
                        ),
                    )
                    SpacerExtraSmall()
                    CTTextView(
                        text = TextSpec.Resources(R.string.startCoopAlert_warning),
                        style = CTTheme.typography.caption,
                        color = CTTheme.color.placeholder,
                    )
                }
            },
            alertDialogAction = actions,
            onDismissRequest = {
                closeDialog()
                onDismiss?.invoke()
            },
        )
    }
}
