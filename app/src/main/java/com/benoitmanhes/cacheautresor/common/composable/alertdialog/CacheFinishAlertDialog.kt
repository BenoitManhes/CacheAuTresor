package com.benoitmanhes.cacheautresor.common.composable.alertdialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.alertdialog.AlertDialogState
import com.benoitmanhes.designsystem.atoms.text.CTMarkdownText
import com.benoitmanhes.designsystem.molecule.alertdialog.AlertDialogAction
import com.benoitmanhes.designsystem.molecule.alertdialog.CTAlertDialog
import com.benoitmanhes.designsystem.molecule.sticker.CTSticker
import com.benoitmanhes.designsystem.res.icons.iconpack.Crown
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

data class CacheFinishAlertDialog(
    val cacheName: String,
    val ptsWin: TextSpec,
) : AlertDialogState {
    override val actions: List<AlertDialogAction> = listOf(
        AlertDialogAction(
            text = TextSpec.Resources(R.string.cacheDetail_cacheFinish_alertDialog_action),
            type = AlertDialogAction.Type.Confirm,
        ) {},
    )
    override val onDismiss: (() -> Unit)? = null

    @Composable
    override fun Content(closeDialog: () -> Unit) {
        CTAlertDialog(
            title = TextSpec.Resources(R.string.cacheDetail_cacheFinish_alertDialog_title),
            alertDialogAction = actions,
            icon = CTTheme.icon.Crown.toIconSpec(),
            onDismissRequest = {
                closeDialog()
                onDismiss?.invoke()
            },
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
                ) {
                    CTMarkdownText(
                        markdown = TextSpec.Resources(
                            id = R.string.cacheDetail_cacheFinish_alertDialog_message,
                            cacheName
                        ),
                        style = CTTheme.typography.bodySmall,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    CTSticker(label = ptsWin)
                }
            },
        )
    }
}
