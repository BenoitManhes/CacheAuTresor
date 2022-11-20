package com.benoitmanhes.designsystem.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Dimens {
    object Spacing {
        val extraSmall: Dp = 2.dp
        val verySmall: Dp = 4.dp
        val small: Dp = 8.dp
        val medium: Dp = 12.dp
        val large: Dp = 16.dp
        val veryLarge: Dp = 24.dp
        val extraLarge: Dp = 32.dp
        val huge: Dp = 48.dp
    }

    object Elevation {
        val none: Dp = 0.dp
        val small: Dp = 2.dp
        val medium: Dp = 8.dp
    }

    object Font {
        val header1FontSize: TextUnit = 24.sp
        val bodyFontSize: TextUnit = 18.sp
        val bodySmallFontSize: TextUnit = 15.sp
        val captionFontSize: TextUnit = 12.sp
    }

    object Corner {
        val small: Dp = 8.dp
        val medium: Dp = 12.dp
        val large: Dp = 24.dp
        const val percentRounded: Int = 100
    }

    object Stroke {
        val thin: Dp = 1.dp
        val medium: Dp = 2.dp
        val strong: Dp = 3.dp
    }

    object Size
}
