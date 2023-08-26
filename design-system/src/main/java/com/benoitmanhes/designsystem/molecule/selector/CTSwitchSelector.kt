package com.benoitmanhes.designsystem.molecule.selector

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun CTSwitchSelector(
    items: List<SelectorItem>,
    selectedItem: SelectorItem,
    onSelectedItem: (SelectorItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Selector(
        items = items,
        selectedItem = selectedItem,
        onSelectedItem = onSelectedItem,
        modifier = modifier,
        shape = CTTheme.shape.circle,
        selectorShape = CTTheme.shape.circle,
    )
}