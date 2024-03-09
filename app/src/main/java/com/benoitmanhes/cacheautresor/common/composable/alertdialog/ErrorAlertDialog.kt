package com.benoitmanhes.cacheautresor.common.composable.alertdialog

import androidx.compose.runtime.Composable
import com.benoitmanhes.cacheautresor.screen.alertdialog.AlertDialogState
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.text.CTMarkdownText
import com.benoitmanhes.designsystem.molecule.alertdialog.AlertDialogAction
import com.benoitmanhes.designsystem.molecule.alertdialog.CTAlertDialog
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.utils.IconSpec

data class ErrorAlertDialog(
    override val actions: List<AlertDialogAction>,
    val title: TextSpec? = null,
    val message: TextSpec? = null,
    val icon: ComposeProvider<IconSpec>? = null,
    override val onDismiss: (() -> Unit)? = null,
) : AlertDialogState {

    @Composable
    override fun Content(closeDialog: () -> Unit) {
        CTAlertDialog(
            title = title,
            icon = icon?.invoke(),
            iconColor = CTTheme.color.textCritical,
            content = {
                message?.let {
                    CTMarkdownText(markdown = message)
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
