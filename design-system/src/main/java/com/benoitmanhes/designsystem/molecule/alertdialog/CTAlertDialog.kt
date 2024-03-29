package com.benoitmanhes.designsystem.molecule.alertdialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ComposableContent
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec

@Composable
fun CTAlertDialog(
    title: TextSpec?,
    content: ComposableContent,
    alertDialogAction: List<AlertDialogAction>,
    onDismissRequest: () -> Unit,
    icon: IconSpec? = null,
    iconColor: Color = CTTheme.color.primary,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            color = CTTheme.color.surface,
            contentColor = CTTheme.color.textOnSurface,
            shape = CTTheme.shape.medium,
        ) {
            Column {
                Column(
                    modifier = Modifier.padding(CTTheme.spacing.veryLarge),
                    verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    icon?.let {
                        CTIcon(
                            icon = icon,
                            size = Dimens.IconSize.Immense,
                            color = iconColor,
                        )
                    }
                    CTTextView(
                        text = title,
                        style = CTTheme.typography.bodyBold,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    content()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = CTTheme.spacing.medium),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    alertDialogAction.sortedBy { action ->
                        when (action.type) {
                            AlertDialogAction.Type.Cancel -> 0
                            AlertDialogAction.Type.Neutral -> 1
                            AlertDialogAction.Type.Confirm -> 2
                            AlertDialogAction.Type.Dangerous -> 3
                        }
                    }.map { action ->
                        action
                            .copy(
                                onClick = {
                                    action.onClick()
                                    onDismissRequest()
                                }
                            )
                            .Content()
                    }
                }
            }
        }
    }
}
