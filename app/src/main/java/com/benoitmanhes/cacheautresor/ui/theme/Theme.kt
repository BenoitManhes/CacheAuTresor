package com.benoitmanhes.cacheautresor.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalColor: ProvidableCompositionLocal<AppColorScheme> = staticCompositionLocalOf { DayColorScheme }
private val LocalTypography: ProvidableCompositionLocal<AppTypography> = staticCompositionLocalOf { AppTypography }
private val LocalShape: ProvidableCompositionLocal<AppShape> = staticCompositionLocalOf { AppShape }

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val localTypography = AppTypography
    val localShape = AppShape

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
        LocalShape provides localShape,
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
        ) {
            androidx.compose.material.MaterialTheme(
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
    localColor: AppColorScheme
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
    localColor: AppColorScheme
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
private fun mappedMaterial2Typography(localTypography: AppTypography) = Typography(
    body1 = localTypography.body,
    subtitle1 = localTypography.body,
    caption = localTypography.caption,
)

object AppTheme {
    val typography: AppTypography
        @Composable
        get() = LocalTypography.current

    val colors: AppColorScheme
        @Composable
        get() = LocalColor.current

    val shape: AppShape
        @Composable
        get() = LocalShape.current
}
