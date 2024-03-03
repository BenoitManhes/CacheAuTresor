package com.benoitmanhes.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.res.Colors

typealias GradientColors = List<Color>

@Immutable
object DefaultDayColorScheme : CTColorScheme {
    // Tint
    override val primary: Color = Colors.Marigold
    override val primaryDark: Color = Colors.CarrotOrange
    override val critical: Color = Colors.MaximumRed
    override val disable: Color = Colors.TransparentBlack15

    // Text
    override val textDefault: Color = Colors.Black
    override val textLight: Color = Colors.QuickSilver
    override val textVeryLight: Color = Colors.TransparentBlack15
    override val textPrimary: Color = primary
    override val textDisable: Color = disable
    override val textCritical: Color = critical
    override val textOnSurface: Color = Colors.Black
    override val textOnSurfacePrimary: Color = Colors.White
    override val textOnSurfacePrimarySoft: Color = primary
    override val textOnSurfaceCritical: Color = Colors.White
    override val textOnSurfaceDisable: Color = Colors.White
    override val textOnBackground: Color = Colors.Black

    // Surface
    override val surface: Color = Colors.White
    override val surfaceLight: Color = Colors.TransparentBlack15
    override val surfacePrimary: Color = primary
    override val surfacePrimarySoft: Color = surfacePrimary.copy(alpha = 0.20f)
    override val surfaceCritical: Color = critical
    override val surfaceCriticalSoft: Color = critical.copy(alpha = 0.15f)
    override val surfaceError: Color = Colors.BurntUmber
    override val surfaceDisable: Color = disable

    // Background
    override val background: Color = Colors.White
    override val backgroundMask: Color = Colors.TransparentBlack15
    override val backgroundRoot: Color = Colors.Black

    // Stroke
    override val strokeBorder: Color = Colors.Black
    override val strokeDivider: Color = Colors.QuickSilver
    override val strokeDisable: Color = disable
    override val strokeOnPrimary: Color = Colors.White

    // Ripple
    override val ripple: Color = primary.copy(alpha = .2f)
    override val rippleOnPrimary: Color = textOnSurfacePrimary.copy(alpha = .2f)
    override val rippleNeutral: Color = textDefault.copy(alpha = .2f)

    // Gradient
    override val gradientSurfacePrimary: GradientColors = listOf(primary, primaryDark)
    override val gradientSurfacePrimarySoft: GradientColors = listOf(primary.copy(alpha = 0.35f), primaryDark.copy(alpha = 0.35f))
    override val gradientBackgroundPrimary: GradientColors = listOf(primary.copy(alpha = 0.75f), primaryDark.copy(alpha = 0.75f))
    override val gradientSurfaceCritical: GradientColors = listOf(Colors.Red, critical)
}

// Define a dark mode
// object DefaultNightColorScheme : CTColorScheme

/** Naming convention
 * light/dark: Luminance
 * soft/strong : alpha
 */
@Immutable
interface CTColorScheme {
    // Tint
    val primary: Color
    val primaryDark: Color
    val critical: Color
    val disable: Color

    // Text
    val textDefault: Color
    val textLight: Color
    val textVeryLight: Color
    val textPrimary: Color
    val textDisable: Color
    val textCritical: Color
    val textOnSurface: Color
    val textOnSurfacePrimary: Color
    val textOnSurfacePrimarySoft: Color
    val textOnSurfaceCritical: Color
    val textOnSurfaceDisable: Color
    val textOnBackground: Color

    // Surface
    val surface: Color
    val surfaceLight: Color
    val surfacePrimary: Color
    val surfacePrimarySoft: Color
    val surfaceCritical: Color
    val surfaceCriticalSoft: Color
    val surfaceError: Color
    val surfaceDisable: Color

    // Background
    val background: Color
    val backgroundMask: Color
    val backgroundRoot: Color

    // Stroke
    val strokeBorder: Color
    val strokeDivider: Color
    val strokeDisable: Color
    val strokeOnPrimary: Color

    // Ripple
    val ripple: Color
    val rippleOnPrimary: Color
    val rippleNeutral: Color

    // Gradient
    val gradientSurfacePrimary: GradientColors
    val gradientSurfacePrimarySoft: GradientColors
    val gradientBackgroundPrimary: GradientColors
    val gradientSurfaceCritical: GradientColors

    fun copy(
        primaryColor: Color? = null,
        primaryDarkColor: Color? = null,
    ): CTColorScheme = object : CTColorScheme {
        // Tint
        override val primary: Color = primaryColor ?: this@CTColorScheme.primary
        override val primaryDark: Color = primaryDarkColor ?: this@CTColorScheme.primaryDark
        override val critical: Color = this@CTColorScheme.critical
        override val disable: Color = this@CTColorScheme.disable

        // Text
        override val textDefault: Color = this@CTColorScheme.textDefault
        override val textLight: Color = this@CTColorScheme.textLight
        override val textVeryLight: Color = this@CTColorScheme.textVeryLight
        override val textPrimary: Color = primary
        override val textDisable: Color = this@CTColorScheme.textDisable
        override val textCritical: Color = this@CTColorScheme.textCritical
        override val textOnSurface: Color = this@CTColorScheme.textOnSurface
        override val textOnSurfacePrimary: Color = this@CTColorScheme.textOnSurfacePrimary
        override val textOnSurfacePrimarySoft: Color = primary
        override val textOnSurfaceCritical: Color = this@CTColorScheme.textOnSurfaceCritical
        override val textOnSurfaceDisable: Color = this@CTColorScheme.textOnSurfaceDisable
        override val textOnBackground: Color = this@CTColorScheme.textOnBackground

        // Surface
        override val surface: Color = this@CTColorScheme.surface
        override val surfaceLight: Color = this@CTColorScheme.surfaceLight
        override val surfacePrimary: Color = primary
        override val surfacePrimarySoft: Color = surfacePrimary.copy(alpha = 0.20f)
        override val surfaceCritical: Color = this@CTColorScheme.surfaceCritical
        override val surfaceCriticalSoft: Color = this@CTColorScheme.surfaceCriticalSoft
        override val surfaceError: Color = this@CTColorScheme.surfaceError
        override val surfaceDisable: Color = this@CTColorScheme.surfaceDisable

        // Background
        override val background: Color = Colors.White
        override val backgroundMask: Color = Colors.TransparentBlack15
        override val backgroundRoot: Color = Colors.Black

        // Ripple
        override val ripple: Color = primary.copy(alpha = .2f)
        override val rippleOnPrimary: Color = textOnSurfacePrimary.copy(alpha = .2f)
        override val rippleNeutral: Color = textDefault.copy(alpha = .2f)

        // Stroke
        override val strokeBorder: Color = Colors.Black
        override val strokeDivider: Color = Colors.QuickSilver
        override val strokeDisable: Color = disable
        override val strokeOnPrimary: Color = this@CTColorScheme.strokeOnPrimary

        // Gradient
        override val gradientSurfacePrimary: GradientColors = listOf(primary, primaryDark)
        override val gradientSurfacePrimarySoft: GradientColors = listOf(primary.copy(alpha = 0.35f), primaryDark.copy(alpha = 0.35f))
        override val gradientBackgroundPrimary: GradientColors = listOf(primary.copy(alpha = 0.75f), primaryDark.copy(alpha = 0.75f))
        override val gradientSurfaceCritical: GradientColors = this@CTColorScheme.gradientSurfaceCritical
    }
}
