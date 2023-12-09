package com.benoitmanhes.designsystem.molecule.modalBottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTMarkdownText
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ComposableContent
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun CTModalBottomSheetContent(
    hide: () -> Unit,
    modifier: Modifier = Modifier,
    icon: IconSpec? = null,
    title: TextSpec? = null,
    message: TextSpec? = null,
    cancelAction: PrimaryButtonState? = null,
    confirmAction: PrimaryButtonState? = null,
    color: Color = CTTheme.color.primary,
    content: ComposableContent? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = CTTheme.spacing.large, vertical = CTTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
    ) {
        icon?.let { _icon ->
            CTIcon(
                icon = _icon,
                size = Dimens.IconSize.Immense,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = color,
            )
        }

        title?.let { _title ->
            CTTextView(
                text = title,
                style = CTTheme.typography.bodyBold,
                color = CTTheme.color.onSurface,
            )
        }

        message?.let { rawMessage ->
            CTMarkdownText(
                markdown = rawMessage,
                style = CTTheme.typography.body,
                color = CTTheme.color.onSurface,
            )
        }
    }

    content?.invoke()

    // Action
    if (cancelAction != null || confirmAction != null) {
        Column(
            modifier = Modifier.padding(CTTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
        ) {
            cancelAction?.copy(
                type = PrimaryButtonType.OUTLINED,
                onClick = {
                    hide()
                    cancelAction.onClick()
                },
            )?.Content(
                modifier = Modifier.fillMaxWidth(),
            )

            confirmAction?.copy(
                type = PrimaryButtonType.COLORED,
                onClick = {
                    hide()
                    confirmAction.onClick()
                },
            )?.Content(
                modifier = Modifier.fillMaxWidth(),
                color = color,
            )
        }
    }
}
