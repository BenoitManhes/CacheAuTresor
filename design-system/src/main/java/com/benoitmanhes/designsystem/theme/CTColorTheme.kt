package com.benoitmanhes.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.benoitmanhes.designsystem.res.Colors

@Immutable
sealed interface CTColorTheme {
    val dayColorScheme: CTColorScheme
    val nightColorScheme: CTColorScheme

    fun getCurrentColorScheme(isDarkMode: Boolean): CTColorScheme =
        //        if (isDarkMode) nightColorScheme else dayColorScheme
        dayColorScheme // Only dayMode now

    val color: CTColorScheme
        @Composable get() {
            return getCurrentColorScheme(isSystemInDarkTheme())
        }

    @Immutable
    data object Default : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme
        override val nightColorScheme: CTColorScheme = dayColorScheme
    }

    @Immutable
    data object Explore : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.Marigold,
            primaryDarkColor = Colors.CarrotOrange,
        )

        override val nightColorScheme: CTColorScheme = dayColorScheme
    }

    @Immutable
    data object Cartography : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.DarkCerulean,
            primaryDarkColor = Colors.SpaceCadet,
        )

        override val nightColorScheme: CTColorScheme = dayColorScheme
    }

    @Immutable
    data object Lock : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.QuickSilver,
            primaryDarkColor = Colors.OldSilver,
        )

        override val nightColorScheme: CTColorScheme = dayColorScheme
    }

    @Immutable
    data object Classical : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.LaSalleGreen,
            primaryDarkColor = Colors.CalPolyPomonaGreen,
        )

        override val nightColorScheme: CTColorScheme = dayColorScheme
    }

    @Immutable
    data object Coop : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.LightSeaGreen,
            primaryDarkColor = Colors.CyanCornflowerBlue,
        )

        override val nightColorScheme: CTColorScheme = dayColorScheme
    }

    @Immutable
    data object Mystery : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.MetallicViolet,
            primaryDarkColor = Colors.DeepViolet,
        )

        override val nightColorScheme: CTColorScheme = dayColorScheme
    }

    @Immutable
    data object Piste : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.WatermelonRed,
            primaryDarkColor = Colors.RedViolet,
        )

        override val nightColorScheme: CTColorScheme = dayColorScheme
    }
}
