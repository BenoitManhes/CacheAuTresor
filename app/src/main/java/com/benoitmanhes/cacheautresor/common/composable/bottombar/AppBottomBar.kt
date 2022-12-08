package com.benoitmanhes.cacheautresor.common.composable.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.benoitmanhes.cacheautresor.common.composable.divider.Spacer
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.designsystem.theme.spacing
import com.benoitmanhes.designsystem.theme.typo

@Composable
fun <I : BottomBarItem> AppBottomBar(
    bottomBarItems: List<I>,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor.copy(alpha = ContentAlpha.medium),
    itemIsSelected: (I) -> Boolean = { false },
    onItemSelected: ((I) -> Unit)? = null,
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = backgroundColor,
    ) {
        bottomBarItems.forEach { bottomBarItem ->
            AppBottomNavigationBarItem(
                item = bottomBarItem,
                isSelected = itemIsSelected(bottomBarItem),
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                onClick = {
                    onItemSelected?.invoke(bottomBarItem)
                }
            )
        }
    }
}

@Composable
private fun <I : BottomBarItem> RowScope.AppBottomNavigationBarItem(
    item: I,
    isSelected: Boolean,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    onClick: () -> Unit,
) {
    val tint by animateColorAsState(if (isSelected) selectedContentColor else unselectedContentColor)

    BottomNavigationItem(
        icon = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Crossfade(targetState = isSelected) { isIconFilled ->
                    if (isIconFilled) {
                        Icon(
                            modifier = Modifier.size(Dimens.ComponentSize.bottomBarItemSize),
                            imageVector = item.iconSelected,
                            contentDescription = null,
                            tint = selectedContentColor,
                        )
                    } else {
                        Icon(
                            modifier = Modifier.size(Dimens.ComponentSize.bottomBarItemSize),
                            imageVector = item.iconUnselected,
                            contentDescription = null,
                            tint = unselectedContentColor,
                        )
                    }
                }
                AnimatedVisibility(visible = isSelected) {
                    Column(
                        modifier = Modifier.wrapContentSize(),
                    ) {
                        Spacer(MaterialTheme.spacing.extraSmall)
                        Text(
                            text = stringResource(id = item.labelRes),
                            style = MaterialTheme.typo.captionBold,
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
