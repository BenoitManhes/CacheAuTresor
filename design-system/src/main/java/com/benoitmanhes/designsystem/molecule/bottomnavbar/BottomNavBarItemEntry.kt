package com.benoitmanhes.designsystem.molecule.bottomnavbar

import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.ComposeProvider

interface BottomNavBarItemEntry {
    val selectedIcon: ComposeProvider<IconSpec>
    val unselectedIcon: ComposeProvider<IconSpec>
    val labelText: TextSpec
}
