package com.benoitmanhes.designsystem.molecule.bottomnavbar

import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec

interface BottomNavBarItemEntry {
    val selectedIcon: IconSpec
    val unselectedIcon: IconSpec
    val labelText: TextSpec
}
