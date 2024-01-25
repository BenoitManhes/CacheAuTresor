package com.benoitmanhes.designsystem.molecule.selector

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun CTTabSelector(
    tabSelectorState: TabSelectorState,
    modifier: Modifier = Modifier,
) {
    Selector(
        items = tabSelectorState.items,
        selectedItem = tabSelectorState.selectedItem,
        onSelectedItem = tabSelectorState.onSelectedItem,
        modifier = modifier.fillMaxWidth(),
        shape = CTTheme.shape.medium,
        contentColor = CTTheme.color.textOnSurfacePrimary,
        selectorShape = RoundedCornerShape(Dimens.Corner.medium - Dimens.Spacing.micro)
    )
}
