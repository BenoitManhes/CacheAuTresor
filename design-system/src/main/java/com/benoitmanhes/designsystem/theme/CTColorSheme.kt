package com.benoitmanhes.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.res.Colors

@Immutable
object DefaultDayColorScheme : CTColorScheme {
    override val primary: Color = Colors.Marigold
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
    override val onError: Color = Colors.White
    override val rootBackground: Color = Colors.Black
    override val backgroundMask: Color = Colors.TransparentBlack15
    override val critical: Color = Colors.BurntUmber
    override val criticalSurface: Color = critical.copy(alpha = 0.15f)
    override val criticalHard: Color = Colors.Red
    override val criticalHardSurface: Color = criticalHard.copy(alpha = 0.15f)
    override val cacheFound: Color = Colors.Marigold
    override val cacheOwned: Color = Colors.SpaceCadet
    override val border: Color = Colors.Black
}

@Immutable
object DefaultNightColorScheme : CTColorScheme {
    override val primary: Color = Colors.Marigold
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
    override val onError: Color = Colors.White
    override val rootBackground: Color = Colors.Black
    override val backgroundMask: Color = Colors.TransparentBlack15
    override val critical: Color = Colors.BurntUmber
    override val criticalSurface: Color = critical.copy(alpha = 0.15f)
    override val criticalHard: Color = Colors.Red
    override val criticalHardSurface: Color = criticalHard.copy(alpha = 0.15f)
    override val cacheFound: Color = Colors.Marigold
    override val cacheOwned: Color = Colors.SpaceCadet
    override val border: Color = Colors.Black
}

@Immutable
interface CTColorScheme {
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
    val error: Color
    val onError: Color
    val rootBackground: Color
    val backgroundMask: Color
    val critical: Color
    val criticalSurface: Color
    val criticalHard: Color
    val criticalHardSurface: Color
    val cacheFound: Color
    val cacheOwned: Color
    val border: Color

    val primarySurface: Color
        get() = primary.copy(alpha = 0.2f)

    fun copy(
        primaryColor: Color? = null,
    ): CTColorScheme = object : CTColorScheme {
        override val primary: Color = primaryColor ?: this@CTColorScheme.primary
        override val onPrimary: Color = this@CTColorScheme.onPrimary
        override val secondary: Color = this@CTColorScheme.secondary
        override val onSecondary: Color = this@CTColorScheme.onSecondary
        override val surface: Color = this@CTColorScheme.surface
        override val onSurface: Color = this@CTColorScheme.onSurface
        override val background: Color = this@CTColorScheme.background
        override val onBackground: Color = this@CTColorScheme.onBackground
        override val placeholder: Color = this@CTColorScheme.placeholder
        override val disable: Color = this@CTColorScheme.disable
        override val onDisable: Color = this@CTColorScheme.onDisable
        override val error: Color = this@CTColorScheme.error
        override val rootBackground: Color = this@CTColorScheme.rootBackground
        override val backgroundMask: Color = this@CTColorScheme.backgroundMask
        override val critical: Color = this@CTColorScheme.critical
        override val criticalSurface: Color = this@CTColorScheme.criticalSurface
        override val criticalHard: Color = this@CTColorScheme.criticalHard
        override val criticalHardSurface: Color = this@CTColorScheme.criticalHardSurface
        override val onError: Color = this@CTColorScheme.onError
        override val cacheFound: Color = this@CTColorScheme.cacheFound
        override val cacheOwned: Color = this@CTColorScheme.cacheOwned
        override val border: Color = this@CTColorScheme.border
    }
}
