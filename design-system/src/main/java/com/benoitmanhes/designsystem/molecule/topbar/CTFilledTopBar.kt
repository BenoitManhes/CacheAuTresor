package com.benoitmanhes.designsystem.molecule.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.text.CTResponsiveText
import com.benoitmanhes.designsystem.molecule.button.iconbutton.CTIconButton
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun CTFilledTopBar(
    title: TextSpec,
    modifier: Modifier = Modifier,
    navAction: CTNavAction? = null,
    trailingAction: CTTopBarAction? = null,
    horizontalArrangement: Arrangement.Horizontal = if (title != null) Arrangement.SpaceEvenly else Arrangement.SpaceBetween,
    content: @Composable (RowScope.() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CTTheme.gradient.surfacePrimary),
    ) {
        Row(
            modifier = modifier
                .statusBarsPadding()
                .requiredHeight(Dimens.TopBar.height)
                .fillMaxWidth()
                .padding(
                    start = CTTheme.spacing.small,
                    end = CTTheme.spacing.large,
                ),
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            navAction?.let {
                CTIconButton(
                    icon = navAction.icon,
                    size = Dimens.IconButtonSize.Medium,
                    iconColor = CTTheme.color.textOnSurfacePrimary,
                    backgroundColor = Color.Transparent,
                    onClick = navAction.onClick,
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = CTTheme.spacing.small)
                    .wrapContentHeight(),
            ) {
                CTResponsiveText(
                    text = title,
                    minFontSize = CTTheme.typography.bodySmall.fontSize,
                    style = CTTheme.typography.header1,
                    color = CTTheme.color.textOnSurfacePrimary,
                )
            }

            content?.invoke(this)

            trailingAction?.let {
                CTIconButton(
                    icon = trailingAction.icon,
                    size = Dimens.IconButtonSize.Medium,
                    iconColor = CTTheme.color.textOnSurfacePrimary,
                    backgroundColor = Color.Transparent,
                    onClick = trailingAction.onClick,
                )
            }
        }
    }
}
