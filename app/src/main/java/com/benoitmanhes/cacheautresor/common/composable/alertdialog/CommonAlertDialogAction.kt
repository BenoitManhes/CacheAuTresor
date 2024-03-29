package com.benoitmanhes.cacheautresor.common.composable.alertdialog

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.designsystem.molecule.alertdialog.AlertDialogAction
import com.benoitmanhes.common.compose.text.TextSpec

object CommonAlertDialogAction {
    fun no(onClick: () -> Unit): AlertDialogAction {
        return AlertDialogAction(
            text = TextSpec.Resources(R.string.common_alertDialog_action_no),
            type = AlertDialogAction.Type.Cancel,
            onClick = onClick,
        )
    }

    fun letsGo(onClick: () -> Unit): AlertDialogAction {
        return AlertDialogAction(
            text = TextSpec.Resources(R.string.common_alertDialog_action_letsGo),
            type = AlertDialogAction.Type.Confirm,
            onClick = onClick,
        )
    }

    fun gotIt(onClick: () -> Unit): AlertDialogAction {
        return AlertDialogAction(
            text = TextSpec.Resources(R.string.common_alertDialog_action_gotIt),
            type = AlertDialogAction.Type.Confirm,
            onClick = onClick,
        )
    }
}
