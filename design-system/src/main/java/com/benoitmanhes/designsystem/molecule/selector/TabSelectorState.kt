package com.benoitmanhes.designsystem.molecule.selector

data class TabSelectorState(
    val items: List<SelectorItem>,
    val selectedItem: SelectorItem,
    val onSelectedItem: (SelectorItem) -> Unit,
)
