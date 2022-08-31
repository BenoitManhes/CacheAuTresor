package com.benoitmanhes.cacheautresor.common.composable.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun <I : BottomBarItem> AppBottomBar(
    bottomBarItems: List<I>,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor.copy(alpha = ContentAlpha.medium),
    onItemSelected: ((I) -> Unit)? = null,
) {
    var indexSelected: Int by rememberSaveable { mutableStateOf(0) }

    BottomNavigation(
        modifier = modifier,
        backgroundColor = backgroundColor,
    ) {
        bottomBarItems.forEachIndexed { index, bottomBarItem ->
            AppBottomNavigationBarItem(
                item = bottomBarItem,
                isSelected = index == indexSelected,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                onClick = {
                    indexSelected = index
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
                    Text(
                        text = stringResource(id = item.labelRes),
                        style = AppTheme.typography.captionBold,
                        color = tint,
                    )
                }
            }
        },
        selected = isSelected,
        onClick = onClick,
    )
}

@Preview
@Composable
private fun PreviewAppBottomBar() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            AppBottomBar(
                bottomBarItems = listOf(
                    AppBottomBarItem.Home,
                    AppBottomBarItem.Favourites,
                    AppBottomBarItem.Explore,
                    AppBottomBarItem.Instruments,
                    AppBottomBarItem.Profile,
                ),
                selectedContentColor = AppTheme.colors.primary,
                unselectedContentColor = AppTheme.colors.placeholder,
                backgroundColor = AppTheme.colors.surface,
            )
        }
    }
}