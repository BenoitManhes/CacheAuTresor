package com.benoitmanhes.cacheautresor.ui.res

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Dimens {
    object Font {
        val header1FontSize: TextUnit = 20.sp
        val bodyFontSize: TextUnit = 15.sp
        val bodySmallFontSize: TextUnit = 12.sp
        val captionFontSize: TextUnit = 10.sp
    }

    object Radius {
        val small: Dp = 4.dp
        val medium: Dp = 8.dp
        val large: Dp = 12.dp
    }

    object ComponentSize {
        val bottomBarItemSize: Dp = 28.dp
    }

    object Elevation {
        val none: Dp = 0.dp
    }
    
    object Stroke {
        val medium: Dp = 1.dp
    }
}