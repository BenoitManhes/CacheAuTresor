package com.benoitmanhes.cacheautresor.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Black: Color = Color(0xFF000000)
val BurntUmber: Color = Color(0xFF832E33)
val Marigold: Color = Color(0xFFF0B02C)
val White: Color = Color(0xFFFFFFFF)

val TransparentBlack10: Color = Color(0x1A000000)
val TransparentBlack35: Color = Color(0x59000000)
val TransparentWhite10: Color = Color(0x1AFFFFFF)
val TransparentWhite35: Color = Color(0x59FFFFFF)

val Neutral10: Color = Color(red = 28, green = 27, blue = 31)
val Neutral90: Color = Color(red = 230, green = 225, blue = 229)

@Immutable
object DayColorScheme : AppColorScheme {
    override val primary: Color = Marigold
    override val onPrimary: Color = White
    override val secondary: Color = BurntUmber
    override val onSecondary: Color = White
    override val surface: Color = White
    override val onSurface: Color = Black
    override val background: Color = White
    override val onBackground: Color = Black
    override val placeholder: Color = TransparentBlack35
    override val disable: Color = TransparentBlack10
    override val onDisable: Color = White
}

@Immutable
object NightColorScheme : AppColorScheme {
    override val primary: Color = Marigold
    override val onPrimary: Color = White
    override val secondary: Color = BurntUmber
    override val onSecondary: Color = White
    override val surface: Color = Neutral10
    override val onSurface: Color = Neutral90
    override val background: Color = Neutral10
    override val onBackground: Color = Neutral90
    override val placeholder: Color = TransparentWhite35
    override val disable: Color = TransparentWhite10
    override val onDisable: Color = Neutral90
}

@Immutable
interface AppColorScheme {
    val primary: Color
    val onPrimary: Color
    val secondary: Color
    val onSecondary: Color
    val surface: Color
    val onSurface: Color
    val background: Color
    val onBackground: Color
    val placeholder: Color
    val disable: Color
    val onDisable: Color
}
