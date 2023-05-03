package com.benoitmanhes.designsystem.molecule.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.atoms.spacer.SpacerMedium
import com.benoitmanhes.designsystem.atoms.text.CTResponsiveText
import com.benoitmanhes.designsystem.molecule.button.CTIconButton
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun CTTopBar(
    modifier: Modifier = Modifier,
    title: TextSpec? = null,
    navIcon: CTTopBarOption.NavIcon? = null,
    actions: List<CTTopBarOption.ActionIcon> = emptyList(),
    horizontalArrangement: Arrangement.Horizontal = if (title != null) Arrangement.SpaceEvenly else Arrangement.SpaceBetween,
    content: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .requiredHeight(Dimens.TopBar.height)
            .fillMaxWidth()
            .padding(CTTheme.spacing.small),
    ) {

        Row(
            modifier = Modifier
                .padding(start = Dimens.TopBar)
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            navIcon?.let {
                CTIconButton(
                    icon = navIcon.icon,
                    onClick = navIcon.onClick,
                )
            }

            CTResponsiveText(
                text = title,
                minFontSize = CTTheme.typography.bodySmall.fontSize,
                style = CTTheme.typography.header1,
            )

            actions.forEach { action ->
                SpacerMedium()
                action.content()
            }
        }
    }

}