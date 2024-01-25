package com.benoitmanhes.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

object CTGradient {
    val surfacePrimary: Brush
        @Composable get() = Brush.linearGradient(
            listOf(CTTheme.color.gradientSurfacePrimaryStart, CTTheme.color.gradientSurfacePrimaryEnd)
        )

    val surfacePrimarySoft: Brush
        @Composable get() = Brush.linearGradient(
            listOf(CTTheme.color.gradientSurfacePrimarySoftStart, CTTheme.color.gradientSurfacePrimarySoftEnd)
        )

    val backgroundPrimary: Brush
        @Composable get() = Brush.linearGradient(
            listOf(CTTheme.color.gradientBackgroundPrimaryStart, CTTheme.color.gradientBackgroundPrimaryEnd)
        )

    val surfaceCritical: Brush
        @Composable get() = Brush.linearGradient(
            listOf(CTTheme.color.gradientSurfaceCriticalStart, CTTheme.color.gradientSurfaceCriticalEnd)
        )
}
