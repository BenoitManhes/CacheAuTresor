package com.benoitmanhes.designsystem.molecule.bottomnavbar

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun <I : BottomNavBarItemEntry> CTBottomNavBar(
    bottomBarItems: List<I>,
    modifier: Modifier = Modifier,
    backgroundColor: Color = CTTheme.color.surface,
    itemIsSelected: (I) -> Boolean = { false },
    onItemSelected: ((I) -> Unit)? = null,
) {
    BottomAppBar(
        backgroundColor = CTTheme.color.surface,
        cutoutShape = CTTheme.shape.circle,
        contentPadding = CTTheme.padding.zero,
    ) {
        BottomNavigation(
            modifier = modifier,
            backgroundColor = backgroundColor,
        ) {
            bottomBarItems.map { item ->
                BottomNavBarItem(
                    isSelected = itemIsSelected(item),
                    selectedIcon = item.selectedIcon,
                    unselectedIcon = item.unselectedIcon,
                    labelText = item.labelText,
                    onClick = { onItemSelected?.invoke(item) },
                )
            }
        }
    }
}
