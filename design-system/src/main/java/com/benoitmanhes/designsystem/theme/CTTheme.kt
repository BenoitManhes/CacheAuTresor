package com.benoitmanhes.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.CTIcons
import com.benoitmanhes.designsystem.utils.ComposableContent

typealias ComposeProvider<T> = @Composable () -> T

private val LocalColor: ProvidableCompositionLocal<CTColorScheme> = staticCompositionLocalOf { DefaultDayColorScheme }
private val LocalTypography: ProvidableCompositionLocal<CTTypography> = staticCompositionLocalOf { CTTypography }
private val LocalShape: ProvidableCompositionLocal<CTShape> = staticCompositionLocalOf { CTShape }
private val LocalSpacing: ProvidableCompositionLocal<Dimens.Spacing> = staticCompositionLocalOf { Dimens.Spacing }
private val LocalElevation: ProvidableCompositionLocal<Dimens.Elevation> = staticCompositionLocalOf { Dimens.Elevation }
private val LocalStroke: ProvidableCompositionLocal<Dimens.Stroke> = staticCompositionLocalOf { Dimens.Stroke }
private val LocalPadding: ProvidableCompositionLocal<CTPadding> = staticCompositionLocalOf { CTPadding }
private val LocalSize: ProvidableCompositionLocal<Dimens.Size> = staticCompositionLocalOf { Dimens.Size }
private val LocalIcon: ProvidableCompositionLocal<CTIcons> = staticCompositionLocalOf { CTIcons }
private val LocalGradient: ProvidableCompositionLocal<CTGradient> = staticCompositionLocalOf { CTGradient }

object CTTheme {

    val typography: CTTypography
        @Composable get() = LocalTypography.current

    val spacing: Dimens.Spacing
        @Composable get() = LocalSpacing.current

    val shape: CTShape
        @Composable get() = LocalShape.current

    val elevation: Dimens.Elevation
        @Composable get() = LocalElevation.current

    val color: CTColorScheme
        @Composable get() = LocalColor.current

    val stroke: Dimens.Stroke
        @Composable get() = LocalStroke.current

    val padding: CTPadding
        @Composable get() = LocalPadding.current

    val icon: CTIcons
        @Composable get() = LocalIcon.current

    val gradient: CTGradient
        @Composable get() = LocalGradient.current
}

@Composable
fun CTTheme(
    colorTheme: CTColorTheme = CTColorTheme.Default,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val localTypography = CTTypography
    val localColor = colorTheme.color

    val materialColorScheme = mappedMaterialColorScheme(darkTheme, localColor)
    val material2Colors = mappedMaterial2Colors(darkTheme, localColor)

    CompositionLocalProvider(
        LocalColor provides localColor,
        LocalTypography provides localTypography,
        LocalShape provides CTShape,
        LocalSpacing provides Dimens.Spacing,
        LocalElevation provides Dimens.Elevation,
        LocalStroke provides Dimens.Stroke,
        LocalPadding provides CTPadding,
        LocalSize provides Dimens.Size,
        LocalIcon provides CTIcons,
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
    localColor: CTColorScheme,
) = if (darkTheme) {
    darkColorScheme(
        primary = localColor.primary,
        onPrimary = localColor.textOnSurfacePrimary,
        secondary = localColor.primaryDark,
        onSecondary = localColor.textOnSurfacePrimary,
        surface = localColor.surface,
        onSurface = localColor.textOnSurface,
        background = localColor.textOnBackground,
        onBackground = localColor.textOnBackground,
        error = localColor.textCritical,
    )
} else {
    lightColorScheme(
        primary = localColor.primary,
        onPrimary = localColor.textOnSurfacePrimary,
        secondary = localColor.primaryDark,
        onSecondary = localColor.textOnSurfacePrimary,
        surface = localColor.surface,
        onSurface = localColor.textOnSurface,
        background = localColor.textOnBackground,
        onBackground = localColor.textOnBackground,
        error = localColor.textCritical,
    )
}

@Composable
private fun mappedMaterial2Colors(
    darkTheme: Boolean,
    localColor: CTColorScheme,
) = if (darkTheme) {
    darkColors(
        primary = localColor.primary,
        onPrimary = localColor.textOnSurfacePrimary,
        secondary = localColor.primaryDark,
        onSecondary = localColor.textOnSurfacePrimary,
        surface = localColor.surface,
        onSurface = localColor.textOnSurface,
        background = localColor.textOnBackground,
        onBackground = localColor.textOnBackground,
        error = localColor.textCritical,
    )
} else {
    lightColors(
        primary = localColor.primary,
        onPrimary = localColor.textOnSurfacePrimary,
        secondary = localColor.primaryDark,
        onSecondary = localColor.textOnSurfacePrimary,
        surface = localColor.surface,
        onSurface = localColor.textOnSurface,
        background = localColor.textOnBackground,
        onBackground = localColor.textOnBackground,
        error = localColor.textCritical,
    )
}

fun <T> CTTheme.composed(
    factory: @Composable CTTheme.() -> T,
): ComposeProvider<T> = {
    this.factory()
}

@Composable
private fun mappedMaterial2Typography(localTypography: CTTypography) = Typography(
    body1 = localTypography.body,
    subtitle1 = localTypography.body,
    caption = localTypography.caption,
)

@Composable
fun CTTheme.ColorTheme(
    colorTheme: CTColorTheme,
    content: ComposableContent,
) {
    /* For now only light mode is supported */
    //    val localColor = if (isSystemInDarkTheme()) {
    //        colorTheme.nightColorScheme
    //    } else {
    //        colorTheme.dayColorScheme
    //    }

    CompositionLocalProvider(
        LocalColor provides colorTheme.dayColorScheme,
        content = content,
    )
}
