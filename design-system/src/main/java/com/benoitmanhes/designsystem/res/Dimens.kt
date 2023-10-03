package com.benoitmanhes.designsystem.res

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
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
        val captionSmallFontSize: TextUnit = 10.sp
        val cacheCardTitleMinFontSize: TextUnit = 18.sp
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

    /* === Components Size === */

    object SecondaryButton {
        val padding: PaddingValues = PaddingValues(
            vertical = 6.dp,
            horizontal = 12.dp,
        )
    }

    enum class FloatingButtonSize(
        val icon: IconSize,
        val button: Dp,
    ) {
        Large(icon = IconSize.Large, button = IconSlotSize.Large.container)
    }

    enum class IconButtonSize(
        val icon: IconSize,
        val button: Dp,
    ) {
        Medium(icon = IconSize.Medium, button = 48.dp),
    }

    enum class IconSize(
        val dp: Dp,
    ) {
        Small(dp = 16.dp),
        Medium(dp = 24.dp),
        Large(dp = 32.dp),
        Huge(dp = 48.dp),
        BottomBarItem(dp = 28.dp),
    }

    enum class IconSlotSize(
        val icon: IconSize,
        val container: Dp,
    ) {
        Small(icon = IconSize.Small, container = 24.dp),
        Medium(icon = IconSize.Medium, container = 36.dp),
        Large(icon = IconSize.Large, container = 48.dp),
        Huge(icon = IconSize.Huge, container = 64.dp),
    }

    object Jauge {
        val size: DpSize = DpSize(
            width = 84.dp,
            height = 68.dp,
        )
        val circleIndicatorSize: Dp = size.width
        val maxTextSize: Dp = 60.dp
    }

    object Loading {
        val loadingViewSize: Dp = 90.dp
        val compassBackgroundSize: Dp = 65.dp
        val aiguilleSize: DpSize = DpSize(
            width = 18.dp,
            height = 70.dp,
        )
    }

    object Text {
        val zoneTextFieldMinHeight: Dp = 100.dp
    }

    object TopBar {
        val height: Dp = 56.dp
    }

    object Size {
        val bottomBarItemSize: Dp = 28.dp
        val fabButtonSize: Dp = 64.dp
        val fabIconButtonSize: Dp = 42.dp

        val compassButtonSize: Dp = 36.dp

        val selectorMinWidth: Dp = 36.dp
        val selectorHeight: Dp = 36.dp

        val lottieAnimationButton: Dp = 24.dp
        val primaryButtonMinHeight: Dp = 48.dp

        val cacheCardHeight: Dp = 96.dp

        val markerSize: Dp = 24.dp

        val textFieldMinHeight: Dp = 48.dp
        val textFieldMinWidth: Dp = 180.dp
    }
}
