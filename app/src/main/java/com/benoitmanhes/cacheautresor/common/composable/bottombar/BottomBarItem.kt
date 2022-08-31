package com.benoitmanhes.cacheautresor.common.composable.bottombar

import androidx.compose.ui.graphics.vector.ImageVector

interface BottomBarItem {
    val iconSelected: ImageVector
    val iconUnselected: ImageVector
    val labelRes: Int
}