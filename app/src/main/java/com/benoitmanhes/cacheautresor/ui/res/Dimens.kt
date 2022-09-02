package com.benoitmanhes.cacheautresor.ui.res

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Dimens {
    object Font {
        val header1FontSize: TextUnit = 24.sp
        val bodyFontSize: TextUnit = 18.sp
        val bodySmallFontSize: TextUnit = 15.sp
        val captionFontSize: TextUnit = 12.sp
    }

    object Radius {
        val small: Dp = 4.dp
        val medium: Dp = 8.dp
        val large: Dp = 12.dp
    }

    object ComponentSize {
        val bottomBarItemSize: Dp = 28.dp
        val textFieldHeight: Dp = 52.dp
        val textFieldMinHeight: Dp = 36.dp
        val textFieldMinWidth: Dp = 180.dp
    }

    object Elevation {
        val none: Dp = 0.dp
    }
    
    object Stroke {
        val thin: Dp = 1.dp
        val strong: Dp = 3.dp
    }

    object Padding {
        val horizontalTextFieldDefault: Dp = 12.dp
        val topTextFieldDefault: Dp = 8.dp
        val topTextFieldLabel: Dp = 16.dp
        val bottomTextFieldDefault: Dp = 4.dp
        val doubleTextFieldInner: Dp = 2.dp
    }
}