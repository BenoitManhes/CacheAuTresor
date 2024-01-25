package com.benoitmanhes.designsystem.molecule.bottomnavbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.atoms.spacer.SpacerExtraSmall
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec

@Composable
internal fun RowScope.BottomNavBarItem(
    isSelected: Boolean,
    selectedIcon: IconSpec,
    unselectedIcon: IconSpec,
    labelText: TextSpec,
    selectedContentColor: Color = CTTheme.color.primary,
    unselectedContentColor: Color = CTTheme.color.textLight,
    onClick: () -> Unit,
) {
    val tint by animateColorAsState(
        if (isSelected) selectedContentColor else unselectedContentColor,
        label = "animate-color"
    )

    BottomNavigationItem(
        icon = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Crossfade(targetState = isSelected, label = "crossfade-cticon") { isSelectedIcon ->
                    if (isSelectedIcon) {
                        CTIcon(
                            icon = selectedIcon,
                            size = Dimens.IconSize.BottomBarItem,
                            color = selectedContentColor,
                        )
                    } else {
                        CTIcon(
                            icon = unselectedIcon,
                            size = Dimens.IconSize.BottomBarItem,
                            color = unselectedContentColor,
                        )
                    }
                }
                AnimatedVisibility(visible = isSelected) {
                    Column(
                        modifier = Modifier.wrapContentSize(),
                    ) {
                        SpacerExtraSmall()
                        CTTextView(
                            text = labelText,
                            style = CTTheme.typography.captionBold,
                            color = tint,
                        )
                    }
                }
            }
        },
        selected = isSelected,
        onClick = onClick,
    )
}
