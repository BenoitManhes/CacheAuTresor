package com.benoitmanhes.cacheautresor.common.composable.alertdialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.alertdialog.AlertDialogState
import com.benoitmanhes.designsystem.atoms.text.CTMarkdownText
import com.benoitmanhes.designsystem.molecule.alertdialog.AlertDialogAction
import com.benoitmanhes.designsystem.molecule.alertdialog.CTAlertDialog
import com.benoitmanhes.designsystem.res.icons.iconpack.DoneStamp
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

data class StepCompleteAlertDialog(
    val stepName: TextSpec,
) : AlertDialogState {
    override val onDismiss: (() -> Unit)? = null
    override val actions: List<AlertDialogAction> = listOf(
        AlertDialogAction(
            text = TextSpec.Resources(R.string.cacheDetail_stepComplete_alertDialog_action),
            type = AlertDialogAction.Type.Confirm,
        ) {},
    )

    @Composable
    override fun Content(closeDialog: () -> Unit) {
        val stepName = stepName.string()
        CTAlertDialog(
            title = TextSpec.Resources(R.string.cacheDetail_stepComplete_alertDialog_title),
            alertDialogAction = actions,
            icon = CTTheme.icon.DoneStamp.toIconSpec(),
            onDismissRequest = {
                closeDialog()
                onDismiss?.invoke()
            },
            content = {
                CTMarkdownText(
                    markdown = TextSpec.Resources(
                        id = R.string.cacheDetail_stepComplete_alertDialog_message,
                        stepName.orEmpty()
                    ),
                    style = CTTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
        )
    }
}
