package com.benoitmanhes.designsystem.theme

import androidx.compose.runtime.Immutable
import com.benoitmanhes.designsystem.res.Colors

@Immutable
sealed interface CTColorTheme {
    val dayColorScheme: CTColorScheme
    val nightColorScheme: CTColorScheme

    @Immutable
    data object Default : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme
        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme
    }

    @Immutable
    data object Explore : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.Marigold,
        )

        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme.copy(
            primaryColor = Colors.Marigold,
        )
    }

    @Immutable
    data object Cartography : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.SpaceCadet,
        )

        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme.copy(
            primaryColor = Colors.SpaceCadet,
        )
    }

    @Immutable
    data object Lock : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.QuickSilver,
        )

        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme.copy(
            primaryColor = Colors.QuickSilver,
        )
    }

    @Immutable
    data object Classical : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.LaSalleGreen,
        )

        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme.copy(
            primaryColor = Colors.LaSalleGreen,
        )
    }

    @Immutable
    data object ClassicalStarted : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.MiddleGreen,
        )

        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme.copy(
            primaryColor = Colors.MiddleGreen,
        )
    }

    @Immutable
    data object Coop : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.LightSeaGreen,
        )

        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme.copy(
            primaryColor = Colors.LightSeaGreen,
        )
    }

    @Immutable
    data object CoopStarted : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.PewterBlue,
        )

        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme.copy(
            primaryColor = Colors.PewterBlue,
        )
    }

    @Immutable
    data object Mystery : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.MetallicViolet,
        )

        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme.copy(
            primaryColor = Colors.MetallicViolet,
        )
    }

    @Immutable
    data object MysteryStarted : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.FrenchLilac,
        )

        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme.copy(
            primaryColor = Colors.FrenchLilac,
        )
    }

    @Immutable
    data object Piste : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.WatermelonRed,
        )

        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme.copy(
            primaryColor = Colors.WatermelonRed,
        )
    }

    @Immutable
    data object PisteStarted : CTColorTheme {
        override val dayColorScheme: CTColorScheme = DefaultDayColorScheme.copy(
            primaryColor = Colors.NewYorkPink,
        )

        override val nightColorScheme: CTColorScheme = DefaultNightColorScheme.copy(
            primaryColor = Colors.NewYorkPink,
        )
    }
}
