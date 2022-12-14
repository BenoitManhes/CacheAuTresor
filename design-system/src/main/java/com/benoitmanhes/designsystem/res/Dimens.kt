package com.benoitmanhes.designsystem.res

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Dimens {
    object Spacing {
        val micro: Dp = 2.dp
        val extraSmall: Dp = 4.dp
        val small: Dp = 8.dp
        val medium: Dp = 12.dp
        val large: Dp = 16.dp
        val veryLarge: Dp = 24.dp
        val extraLarge: Dp = 32.dp
        val huge: Dp = 48.dp
        val immense: Dp = 64.dp
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

    object Padding {
        val none: Dp = 0.dp
        val horizontalTextFieldDefault: Dp = 12.dp
        val topTextFieldDefault: Dp = 8.dp
        val topTextFieldLabel: Dp = 16.dp
        val bottomTextFieldDefault: Dp = 4.dp
    }

    object Stroke {
        val thin: Dp = 1.dp
        val medium: Dp = 2.dp
        val strong: Dp = 3.dp
    }

    object Size {
        val bottomBarItemSize: Dp = 28.dp

        val lottieAnimationButton: Dp = 24.dp
        val primaryButtonMinHeight: Dp = 48.dp

        val mediumIconSize: Dp = 24.dp
        val largeIconSize: Dp = 36.dp

        val textFieldMinHeight: Dp = 48.dp
        val textFieldMinWidth: Dp = 180.dp
    }
}
