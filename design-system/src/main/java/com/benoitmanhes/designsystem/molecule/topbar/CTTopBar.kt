package com.benoitmanhes.designsystem.molecule.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.atoms.text.CTResponsiveText
import com.benoitmanhes.designsystem.molecule.button.iconbutton.CTIconButton
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun CTTopBar(
    modifier: Modifier = Modifier,
    title: TextSpec? = null,
    navAction: CTNavAction? = null,
    trailingAction: CTTopBarAction? = null,
    horizontalArrangement: Arrangement.Horizontal = if (title != null) Arrangement.SpaceEvenly else Arrangement.SpaceBetween,
    content: @Composable (RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .requiredHeight(Dimens.TopBar.height)
            .fillMaxWidth()
            .padding(horizontal = CTTheme.spacing.large),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        navAction?.let {
            CTIconButton(
                icon = navAction.icon,
                size = Dimens.IconButtonSize.Medium,
                onClick = navAction.onClick,
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
        ) {
            CTResponsiveText(
                text = title,
                minFontSize = CTTheme.typography.bodySmall.fontSize,
                style = CTTheme.typography.header1,
            )
        }

        content?.invoke(this)

        trailingAction?.let {
            CTIconButton(
                icon = trailingAction.icon,
                size = Dimens.IconButtonSize.Medium,
                onClick = trailingAction.onClick
            )
        }
    }
}
