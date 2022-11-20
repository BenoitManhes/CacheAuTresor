package com.benoitmanhes.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalColor: ProvidableCompositionLocal<CTColorScheme> = staticCompositionLocalOf { DayColorScheme }
private val LocalTypography: ProvidableCompositionLocal<CTTypography> = staticCompositionLocalOf { CTTypography }
private val LocalCorner: ProvidableCompositionLocal<Dimens.Corner> = staticCompositionLocalOf { Dimens.Corner }
private val LocalSpacing: ProvidableCompositionLocal<Dimens.Spacing> = staticCompositionLocalOf { Dimens.Spacing }
private val LocalElevation: ProvidableCompositionLocal<Dimens.Elevation> = staticCompositionLocalOf { Dimens.Elevation }

@Composable
fun CTTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val localTypography = CTTypography

    val localColor = if (darkTheme) {
        NightColorScheme
    } else {
        DayColorScheme
    }

    val materialColorScheme = mappedMaterialColorScheme(darkTheme, localColor)
    val material2Colors = mappedMaterial2Colors(darkTheme, localColor)

    CompositionLocalProvider(
        LocalColor provides localColor,
        LocalTypography provides localTypography,
        LocalCorner provides Dimens.Corner,
        LocalSpacing provides Dimens.Spacing,
        LocalElevation provides Dimens.Elevation,
    ) {
        androidx.compose.material3.MaterialTheme(
            colorScheme = materialColorScheme,
        ) {
            MaterialTheme(
                colors = material2Colors,
                typography = mappedMaterial2Typography(localTypography),
                content = content,
            )
        }
    }
}

@Composable
private fun mappedMaterialColorScheme(
    darkTheme: Boolean,
    localColor: CTColorScheme
) = if (darkTheme) {
    darkColorScheme(
        primary = localColor.primary,
        onPrimary = localColor.onPrimary,
        secondary = localColor.secondary,
        onSecondary = localColor.onSecondary,
        surface = localColor.surface,
        onSurface = localColor.onSurface,
        background = localColor.onBackground,
        onBackground = localColor.onBackground,
    )
} else {
    lightColorScheme(
        primary = localColor.primary,
        onPrimary = localColor.onPrimary,
        secondary = localColor.secondary,
        onSecondary = localColor.onSecondary,
        surface = localColor.surface,
        onSurface = localColor.onSurface,
        background = localColor.onBackground,
        onBackground = localColor.onBackground,
    )
}

@Composable
private fun mappedMaterial2Colors(
    darkTheme: Boolean,
    localColor: CTColorScheme
) = if (darkTheme) {
    darkColors(
        primary = localColor.primary,
        onPrimary = localColor.onPrimary,
        secondary = localColor.secondary,
        onSecondary = localColor.onSecondary,
        surface = localColor.surface,
        onSurface = localColor.onSurface,
        background = localColor.onBackground,
        onBackground = localColor.onBackground,
    )
} else {
    lightColors(
        primary = localColor.primary,
        onPrimary = localColor.onPrimary,
        secondary = localColor.secondary,
        onSecondary = localColor.onSecondary,
        surface = localColor.surface,
        onSurface = localColor.onSurface,
        background = localColor.onBackground,
        onBackground = localColor.onBackground,
    )
}

@Composable
private fun mappedMaterial2Typography(localTypography: CTTypography) = Typography(
    body1 = localTypography.body,
    subtitle1 = localTypography.body,
    caption = localTypography.caption,
)

val MaterialTheme.typo: CTTypography
    @Composable get() = LocalTypography.current

val MaterialTheme.spacing: Dimens.Spacing
    @Composable get() = LocalSpacing.current

val MaterialTheme.corner: Dimens.Corner
    @Composable get() = LocalCorner.current

val MaterialTheme.elevation: Dimens.Elevation
    @Composable get() = LocalElevation.current

val MaterialTheme.colorScheme: CTColorScheme
    @Composable
    get() = LocalColor.current
