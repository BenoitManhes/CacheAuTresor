package com.benoitmanhes.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush

@Immutable
object CTGradient {
    val surfacePrimary: Brush
        @Composable get() = Brush.linearGradient(CTTheme.color.gradientSurfacePrimary)

    val surfacePrimarySoft: Brush
        @Composable get() = Brush.linearGradient(CTTheme.color.gradientSurfacePrimarySoft)

    val backgroundPrimary: Brush
        @Composable get() = Brush.linearGradient(CTTheme.color.gradientBackgroundPrimary)

    val surfaceCritical: Brush
        @Composable get() = Brush.linearGradient(CTTheme.color.gradientSurfaceCritical)
}
