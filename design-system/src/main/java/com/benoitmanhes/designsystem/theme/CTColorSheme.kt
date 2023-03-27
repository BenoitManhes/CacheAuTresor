package com.benoitmanhes.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.res.Colors

@Immutable
object DayColorScheme : CTColorScheme {
    override val primary: Color = Colors.Marigold
    override val primaryClassical: Color = Colors.LaSalleGreen
    override val primaryCoop: Color = Colors.LightSeaGreen
    override val primaryMystery: Color = Colors.MetallicViolet
    override val primaryPiste: Color = Colors.WatermelonRed
    override val primaryOwner: Color = Colors.BlackOlive
    override val primaryFound: Color = Colors.Marigold
    override val onPrimary: Color = Colors.White
    override val secondary: Color = Colors.BurntUmber
    override val onSecondary: Color = Colors.White
    override val surface: Color = Colors.White
    override val onSurface: Color = Colors.Black
    override val background: Color = Colors.White
    override val onBackground: Color = Colors.Black
    override val placeholder: Color = Colors.TransparentBlack35
    override val disable: Color = Colors.TransparentBlack10
    override val onDisable: Color = Colors.White
    override val error: Color = Colors.BurntUmber
    override val rootBackground: Color = Colors.Black
}

@Immutable
object NightColorScheme : CTColorScheme {
    override val primary: Color = Colors.Marigold
    override val primaryClassical: Color = Colors.LaSalleGreen
    override val primaryCoop: Color = Colors.LightSeaGreen
    override val primaryMystery: Color = Colors.MetallicViolet
    override val primaryPiste: Color = Colors.WatermelonRed
    override val primaryOwner: Color = Colors.BlackOlive
    override val primaryFound: Color = Colors.Marigold
    override val onPrimary: Color = Colors.White
    override val secondary: Color = Colors.BurntUmber
    override val onSecondary: Color = Colors.White
    override val surface: Color = Colors.Neutral10
    override val onSurface: Color = Colors.Neutral90
    override val background: Color = Colors.Neutral10
    override val onBackground: Color = Colors.Neutral90
    override val placeholder: Color = Colors.TransparentWhite35
    override val disable: Color = Colors.TransparentWhite10
    override val onDisable: Color = Colors.Neutral90
    override val error: Color = Colors.BurntUmber
    override val rootBackground: Color = Colors.Black
}

@Immutable
interface CTColorScheme {
    val primary: Color
    val primaryClassical: Color
    val primaryCoop: Color
    val primaryMystery: Color
    val primaryPiste: Color
    val primaryOwner: Color
    val primaryFound: Color
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
    val error: Color
    val rootBackground: Color
}
