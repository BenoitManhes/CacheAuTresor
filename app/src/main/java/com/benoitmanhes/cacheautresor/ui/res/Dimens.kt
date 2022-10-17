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

    object Margin {
        val small: Dp = 4.dp
        val medium: Dp = 8.dp
        val large: Dp = 16.dp
        val great: Dp = 32.dp
        val huge: Dp = 64.dp
    }

    object Radius {
        val small: Dp = 8.dp
        val medium: Dp = 12.dp
        val large: Dp = 24.dp
    }

    object Size {
        val accountCreationImageSize: Dp = 180.dp
        val loginImageSize: Dp = 128.dp
        val passwordIconSize: Dp = 24.dp
    }

    object ComponentSize {
        val buttonHeight: Dp = 48.dp
        val buttonLoaderSize: Dp = 32.dp
        val bottomBarItemSize: Dp = 28.dp
        val textFieldHeight: Dp = 48.dp
        val textFieldMinHeight: Dp = 36.dp
        val textFieldMinWidth: Dp = 180.dp
    }

    object Elevation {
        val none: Dp = 0.dp
        val small: Dp = 4.dp
        val medium: Dp = 8.dp
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
        val switchText: Dp = 2.dp
    }
}