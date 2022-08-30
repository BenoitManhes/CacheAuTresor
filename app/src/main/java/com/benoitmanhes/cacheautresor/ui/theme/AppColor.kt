package com.benoitmanhes.cacheautresor.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Black: Color = Color(0xFF000000)
val BurntUmber: Color = Color(0xFF832E33)
val Marigold: Color = Color(0xFFF0B02C)
val White: Color = Color(0xFFFFFFFF)

val TransparentBlack15: Color = Color(0x15000000)
val TransparentBlack35: Color = Color(0x35000000)

@Immutable
object DayColorScheme : AppColorScheme {
    override val primary: Color = Marigold
    override val onPrimary: Color = White
    override val secondary: Color = BurntUmber
    override val onSecondary: Color = White
    override val surface: Color = White
    override val onSurface: Color = Black
    override val placeholder: Color = TransparentBlack35
    override val disable: Color = TransparentBlack15

}

@Immutable
object NightColorScheme : AppColorScheme {
    override val primary: Color = Marigold
    override val onPrimary: Color = White
    override val secondary: Color = BurntUmber
    override val onSecondary: Color = White
    override val surface: Color = White
    override val onSurface: Color = Black
    override val placeholder: Color = TransparentBlack35
    override val disable: Color = TransparentBlack15

}

@Immutable
interface AppColorScheme {
    val primary: Color
    val onPrimary: Color
    val secondary: Color
    val onSecondary: Color
    val surface: Color
    val onSurface: Color
    val placeholder: Color
    val disable: Color
}
