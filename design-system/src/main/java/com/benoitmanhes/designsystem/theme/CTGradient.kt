package com.benoitmanhes.designsystem.theme

import androidx.compose.ui.graphics.Brush
import com.benoitmanhes.designsystem.res.Colors

object CTGradient {
    val golden: Brush = Brush.linearGradient(
        listOf(Colors.Marigold, Colors.CarrotOrange)
    )

    val deepBlue: Brush = Brush.linearGradient(
        listOf(Colors.DarkCerulean, Colors.SpaceCadet)
    )
}
